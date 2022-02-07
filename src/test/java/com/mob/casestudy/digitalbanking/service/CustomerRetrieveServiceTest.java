package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.dto.CustomerDto;
import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.entities.enumrator.Language;
import com.mob.casestudy.digitalbanking.entities.enumrator.UserStatus;
import com.mob.casestudy.digitalbanking.exception.ProvidedCustomerNotFoundException;
import com.mob.casestudy.digitalbanking.exception.UserNameAndIdNullException;
import com.mob.casestudy.digitalbanking.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CustomerRetrieveServiceTest {
    @InjectMocks
    CustomerRetrieveService customerRetrieveService;
    @Mock
    CustomerRepository customerRepository;

    @Test
    void Null_UserName_Id() {
        String userName = "";
        String id = "";
        Assertions.assertThrows(UserNameAndIdNullException.class, () -> customerRetrieveService.findCustomerByIdOrUsername(userName, id));
    }

    @Test
    void UserName_WithNull_Id() {
        String userName = "Avada12";
        String id = "";
        Assertions.assertThrows(ProvidedCustomerNotFoundException.class, () -> customerRetrieveService.findCustomerByIdOrUsername(userName, id));
    }

    @Test
    void Id_WithNull_UserName() {
        String userName = null;
        String id = UUID.randomUUID().toString();
        Assertions.assertThrows(ProvidedCustomerNotFoundException.class, () -> customerRetrieveService.findCustomerByIdOrUsername(userName, id));
    }

    @Test
    void validateCustomerById() {
        String userName = "Avada12";
        String id = "";
        Assertions.assertThrows(ProvidedCustomerNotFoundException.class, () -> customerRetrieveService.validateCustomerByIdAndUserName(userName, id));
    }

    @Test
    void validateUserName() {
        String userName = "Avada12";
        Customer customer = Customer.builder().userName("Avada123").firstName("odedra").lastName("odd").phoneNumber("2122345554").email("aodedra@mobbiquityinc.com").preferredLanguages(Language.EN).status(UserStatus.ACTIVE).externalId("").createdBy("").createdOn(LocalDateTime.now()).updatedBy("").updatedOn(LocalDateTime.now()).build();
        Mockito.when(customerRepository.findByUserName(userName)).thenReturn(Optional.of(customer));
        Customer customer1 = customerRetrieveService.validateCustomerByIdAndUserName(userName, "");
        Assertions.assertEquals(customer, customer1);
    }

    @Test
    void validateCustomerByIdAndUserName() {
        Customer customer = new Customer();
        String userName = "Avada12";
        UUID id = UUID.randomUUID();
        Mockito.when(customerRepository.findById(id)).thenReturn(java.util.Optional.of(customer));
        Customer customer1 = customerRetrieveService.validateCustomerByIdAndUserName(userName, id.toString());
        Assertions.assertEquals(customer, customer1);
    }

    @Test
    void FindByUserNameAndId() {
        String userName = "Avada123";
        UUID id = UUID.randomUUID();
        Customer customer = Customer.builder().id(id).userName("Avada123").firstName("odedra").lastName("odd").phoneNumber("2122345554").email("aodedra@mobbiquityinc.com").preferredLanguages(Language.EN).status(UserStatus.ACTIVE).externalId("").createdBy("").createdOn(LocalDateTime.now()).updatedBy("").updatedOn(LocalDateTime.now()).build();
        Mockito.when(customerRepository.findById(id)).thenReturn(java.util.Optional.of(customer));
        CustomerDto customerByIdOrUsername = customerRetrieveService.findCustomerByIdOrUsername(userName, id.toString());
        org.assertj.core.api.Assertions.assertThat(customerByIdOrUsername).usingRecursiveComparison().isEqualTo(customer.toDto());
    }
}