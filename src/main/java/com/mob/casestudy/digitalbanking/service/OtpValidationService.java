package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.dto.CustomerOtpDto;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerOtp;
import com.mob.casestudy.digitalbanking.exception.CustomerNotFoundException;
import com.mob.casestudy.digitalbanking.exception.OtpEmptyOrNullException;
import com.mob.casestudy.digitalbanking.exception.OtpFailedAttemptsException;
import com.mob.casestudy.digitalbanking.exception.OtpInvalidException;
import com.mob.casestudy.digitalbanking.repository.CustomerOtpRepository;
import com.mob.casestudy.digitalbanking.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OtpValidationService {
    private final CustomerRepository customerRepository;
    private final CustomerOtpRepository customerOtpRepository;

    public OtpValidationService(CustomerRepository customerRepository, CustomerOtpRepository customerOtpRepository) {
        this.customerRepository = customerRepository;
        this.customerOtpRepository = customerOtpRepository;
    }

    public void validateCustomerOtp(CustomerOtpDto customerOtpDto) {

        String userName = customerOtpDto.getUserName();
        Customer customer = customerRepository.findByUserName(userName);
        if (Objects.isNull(customer)) {
            throw new CustomerNotFoundException("The Requested Customer Not Found");
        }

        String customerProvidedOtp = customerOtpDto.getOtp();
        if (Objects.isNull(customerProvidedOtp) || customerProvidedOtp.isEmpty()) {
            throw new OtpEmptyOrNullException("Null or Empty Opt not Acceptable");
        }
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
