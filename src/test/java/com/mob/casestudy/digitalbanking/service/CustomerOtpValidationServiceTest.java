package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.dto.CustomerOtpDto;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerOtp;
import com.mob.casestudy.digitalbanking.entities.embeddable.CustomerOtpId;
import com.mob.casestudy.digitalbanking.entities.enumrator.Language;
import com.mob.casestudy.digitalbanking.exception.*;
import com.mob.casestudy.digitalbanking.repository.CustomerOtpRepository;
import com.mob.casestudy.digitalbanking.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerOtpValidationServiceTest {
    @InjectMocks
    CustomerOtpValidationService customerOtpValidationService;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerOtpRepository customerOtpRepository;

    @Test
    void Customer_Not_Found() {
        CustomerOtpDto customerOtpDto = new CustomerOtpDto("Avada", "123456");
        Mockito.when(customerRepository.findByUserName(customerOtpDto.getUserName())).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomerNotFoundException.class, () -> customerOtpValidationService.validateCustomerOtp(customerOtpDto));
    }

    @Test
    void Customer_Found() {
        CustomerOtpDto customerOtpDto = new CustomerOtpDto("Avada", "");
        Customer customer = new Customer();
        Mockito.when(customerRepository.findByUserName(customerOtpDto.getUserName())).thenReturn(Optional.of(customer));
        Assertions.assertThrows(OtpEmptyOrNullException.class, () -> customerOtpValidationService.validateCustomerOtp(customerOtpDto));
    }

    @Test
    void Customer_Found_WithNullOtp() {
        CustomerOtpDto customerOtpDto = new CustomerOtpDto("Avada", null);
        Customer customer = new Customer();
        Mockito.when(customerRepository.findByUserName(customerOtpDto.getUserName())).thenReturn(Optional.of(customer));
        Assertions.assertThrows(OtpEmptyOrNullException.class, () -> customerOtpValidationService.validateCustomerOtp(customerOtpDto));
    }

    @Test
    void Customer_Found_WithExpiredOtp() {
        CustomerOtpDto customerOtpDto = new CustomerOtpDto("Avada", "123456");
        Customer customer = new Customer();
        CustomerOtp customerOtp = CustomerOtp.builder().customerOtpId(new CustomerOtpId()).otpMessage("This is Opt").otp("123456").otpRetries(1).createdOn(LocalDateTime.now()).expiryOn(LocalDateTime.of(LocalDate.of(2022, 1, 31), LocalTime.of(22, 5, 48, 346682))).build();
        customer.setCustomerOtp(customerOtp);
        Mockito.when(customerRepository.findByUserName(customerOtpDto.getUserName())).thenReturn(Optional.of(customer));
        Assertions.assertThrows(OtpInitiatedExpiredException.class, () -> customerOtpValidationService.validateCustomerOtp(customerOtpDto));
    }

    @Test
    void Customer_Found_WithInvalidOtp() {
        CustomerOtpDto customerOtpDto = new CustomerOtpDto("Avada", "123356");
        Customer customer = new Customer();
        CustomerOtp customerOtp = CustomerOtp.builder().customerOtpId(new CustomerOtpId()).otpMessage("This is Opt").otp("122456").otpRetries(1).createdOn(LocalDateTime.now()).expiryOn(LocalDateTime.of(LocalDate.of(2023, 2, 2), LocalTime.of(22, 5, 48, 346682))).build();
        customerOtp.setOtpRetries(2);
        customer.setCustomerOtp(customerOtp);
        String customerProvidedOtp = "123456";
        Mockito.when(customerRepository.findByUserName(customerOtpDto.getUserName())).thenReturn(Optional.of(customer));
        Assertions.assertThrows(OtpInvalidException.class, () -> customerOtpValidationService.validateCustomerOtp(customerOtpDto));
    }

    @Test
    void Provided_Otp_Null() {
        CustomerOtpDto customerOtpDto = new CustomerOtpDto("Avada", null);
        Assertions.assertThrows(OtpEmptyOrNullException.class, () -> customerOtpValidationService.validateNullOrEmptyOtp(customerOtpDto));
    }

    @Test
    void Provided_Otp_Empty() {
        CustomerOtpDto customerOtpDto = new CustomerOtpDto("Avada", "");
        Assertions.assertThrows(OtpEmptyOrNullException.class, () -> customerOtpValidationService.validateNullOrEmptyOtp(customerOtpDto));
    }

    @Test
    void Otp_Not_Expired() {
        Customer customer = Customer.builder().userName("Avada").firstName("odedra").lastName("odd").phoneNumber("2122345554").email("aodedra@mobbiquityinc.com").preferredLanguages(Language.EN).build();
        CustomerOtp customerOtp = CustomerOtp.builder().customerOtpId(new CustomerOtpId()).otpMessage("This is Opt").otp("123456").otpRetries(1).createdOn(LocalDateTime.now()).expiryOn(LocalDateTime.of(LocalDate.of(2022, 12, 1), LocalTime.of(22, 5, 48, 346682))).build();
        customer.setCustomerOtp(customerOtp);
        customerOtpValidationService.validateExpiredOtp(customer);
    }

    @Test
    void Expired_Otp() {
        Customer customer = Customer.builder().userName("Avada").firstName("odedra").lastName("odd").phoneNumber("2122345554").email("aodedra@mobbiquityinc.com").preferredLanguages(Language.EN).build();
        CustomerOtp customerOtp = CustomerOtp.builder().customerOtpId(new CustomerOtpId()).otpMessage("This is Opt").otp("123456").otpRetries(1).createdOn(LocalDateTime.now()).expiryOn(LocalDateTime.of(LocalDate.of(2022, 1, 31), LocalTime.of(12, 5, 48, 346682))).build();
        customer.setCustomerOtp(customerOtp);
        Assertions.assertThrows(OtpInitiatedExpiredException.class, () -> customerOtpValidationService.validateExpiredOtp(customer));
    }

    @Test
    void validate_Invalid_Otp() {
        Customer customer = Customer.builder().userName("Avada").firstName("odedra").lastName("odd").phoneNumber("2122345554").email("aodedra@mobbiquityinc.com").preferredLanguages(Language.EN).build();
        CustomerOtp customerOtp = CustomerOtp.builder().customerOtpId(new CustomerOtpId()).otpMessage("This is Opt").otp("125456").otpRetries(1).createdOn(LocalDateTime.now()).expiryOn(LocalDateTime.of(LocalDate.of(2022, 1, 31), LocalTime.of(12, 5, 48, 346682))).build();
        customer.setCustomerOtp(customerOtp);
        String customerProvidedOtp = "123456";
        Assertions.assertThrows(OtpInvalidException.class, () -> customerOtpValidationService.validateInvalidOtpAndOtpAttempts(customer, customerProvidedOtp));
    }

    @Test
    void Invalid_Otp_Attempt() {
        Customer customer = Customer.builder().userName("Avada").firstName("odedra").lastName("odd").phoneNumber("2122345554").email("aodedra@mobbiquityinc.com").preferredLanguages(Language.EN).build();
        CustomerOtp customerOtp = CustomerOtp.builder().customerOtpId(new CustomerOtpId()).otpMessage("This is Opt").otp("122456").otpRetries(1).createdOn(LocalDateTime.now()).expiryOn(LocalDateTime.of(LocalDate.of(2022, 1, 31), LocalTime.of(12, 5, 48, 346682))).build();
        customer.setCustomerOtp(customerOtp);
        customerOtp.setOtpRetries(3);
        String customerProvidedOtp = "123456";
        Assertions.assertThrows(OtpFailedAttemptsException.class, () -> customerOtpValidationService.validateInvalidOtpAndOtpAttempts(customer, customerProvidedOtp));
    }
}