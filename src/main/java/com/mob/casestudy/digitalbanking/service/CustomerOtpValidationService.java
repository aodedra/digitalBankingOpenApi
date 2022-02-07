package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.dto.CustomerOtpDto;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerOtp;
import com.mob.casestudy.digitalbanking.exception.*;
import com.mob.casestudy.digitalbanking.repository.CustomerOtpRepository;
import com.mob.casestudy.digitalbanking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class CustomerOtpValidationService {
    private final CustomerRepository customerRepository;
    private final CustomerOtpRepository customerOtpRepository;
    @Autowired
    public CustomerOtpValidationService(CustomerRepository customerRepository, CustomerOtpRepository customerOtpRepository) {
        this.customerRepository = customerRepository;
        this.customerOtpRepository = customerOtpRepository;
    }

    public void validateCustomerOtp(CustomerOtpDto customerOtpDto) {
        String userName = customerOtpDto.getUserName();
        String customerProvidedOtp = customerOtpDto.getOtp();
        Customer customer = customerRepository.findByUserName(userName).orElseThrow(()->new CustomerNotFoundException("The Requested Customer Not Found"));
        validateNullOrEmptyOtp(customerOtpDto);
        validateExpiredOtp(customer);
        validateInvalidOtpAndOtpAttempts(customer,customerProvidedOtp);
    }

    public void validateNullOrEmptyOtp(CustomerOtpDto customerOtpDto) {
        String customerProvidedOtp = customerOtpDto.getOtp();
        if (Objects.isNull(customerProvidedOtp) || customerProvidedOtp.isEmpty()) {
            throw new OtpEmptyOrNullException("Null or Empty Opt not Acceptable");
        }
    }

    public void validateExpiredOtp(Customer customer) {
        CustomerOtp customerOtp = customer.getCustomerOtp();
        LocalDateTime expiryOn = customerOtp.getExpiryOn();
        LocalDateTime requestedTime = LocalDateTime.now();
        if (requestedTime.isAfter(expiryOn)) {
            throw new OtpInitiatedExpiredException("Entered Otp is Expired");
        }
    }

    public void validateInvalidOtpAndOtpAttempts(Customer customer,String customerProvidedOtp) {
        CustomerOtp customerOtp = customer.getCustomerOtp();
        Integer otpRetries = customerOtp.getOtpRetries();
        String databaseOtp = customer.getCustomerOtp().getOtp();
        if (otpRetries <= 2) {
            if (!customerProvidedOtp.equals(databaseOtp)) {
                customerOtp.setOtpRetries(++otpRetries);
                customerOtpRepository.saveAndFlush(customerOtp);
                throw new OtpInvalidException("Entered Otp is Invalid");
            }
        } else {
            throw new OtpFailedAttemptsException("The Requested User Otp Attempt not acceptable more than 2 times");
        }
    }
}
