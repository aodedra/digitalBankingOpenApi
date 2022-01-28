package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.CustomerSecurityImages;
import com.mob.casestudy.digitalbanking.entities.enumrator.Language;
import com.mob.casestudy.digitalbanking.entities.enumrator.UserStatus;
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




@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {
    @InjectMocks
    ValidationService validationService;

    @Mock
    CustomerRepository customerRepository;

    String byUserName = "Avada odedra";
    Customer customer=Customer.builder().userName("Avada").firstName("odedra").lastName("odd").phoneNumber("2122345554").email("aodedra@mobbiquityinc.com").preferredLanguages(Language.EN).build();
    Customer noCustomer;
    CustomerSecurityImages csecurityImages;

    @Test
    void validateUser_WithNo_UserNameFound() {
        Mockito.when(customerRepository.findByUserName(byUserName)).thenReturn(noCustomer);
        Assertions.assertThrows(UserNotFoundException.class, () -> validationService.validateUser(byUserName));
    }

    @Test
    void validateUser_WithValid_UserNameFound() {
        Mockito.when(customerRepository.existsByUserName(byUserName)).thenReturn(true);
        Mockito.when(customerRepository.findByUserName(byUserName)).thenReturn(customer);
        validationService.validateUser(byUserName);
        Mockito.verify(customerRepository).findByUserName(byUserName);
    }

    @Test
    void validateImages(){
        CustomerSecurityImages customerSecurityImages=null;
        Assertions.assertThrows(CustomerSecurityImageNotFound.class, () -> validationService.validateImages(customerSecurityImages));

    }
}