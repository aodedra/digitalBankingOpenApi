package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.entities.enumrator.Language;
import com.mob.casestudy.digitalbanking.exception.CustomerSecurityImageNotFound;
import com.mob.casestudy.digitalbanking.exception.UserNotFoundException;
import com.mob.casestudy.digitalbanking.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerValidationServiceTest {
    @InjectMocks
    CustomerValidationService customerValidationService;

    @Mock
    CustomerRepository customerRepository;

    String userName = "Avada odedra";
    Customer customer = Customer.builder().userName("Avada").firstName("odedra").lastName("odd").phoneNumber("2122345554").email("aodedra@mobbiquityinc.com").preferredLanguages(Language.EN).build();
    Customer noCustomer;

    @Test
    void validateUser_WithNot_UserNameFound() {
        Mockito.when(customerRepository.findByUserName(userName)).thenReturn(Optional.ofNullable(noCustomer));
        Assertions.assertThrows(UserNotFoundException.class, () -> customerValidationService.validateCustomer(userName));
    }

    @Test
    void validateUser_WithValid_UserNameFound() {
        Mockito.when(customerRepository.findByUserName(userName)).thenReturn(Optional.of(customer));
        customerValidationService.validateCustomer(userName);
        Mockito.verify(customerRepository).findByUserName(userName);
    }

    @Test
    void validateImages() {
        CustomerSecurityImages customerSecurityImages = null;
        Assertions.assertThrows(CustomerSecurityImageNotFound.class, () -> customerValidationService.validateCustomerSecurityImages(customerSecurityImages));
    }
}