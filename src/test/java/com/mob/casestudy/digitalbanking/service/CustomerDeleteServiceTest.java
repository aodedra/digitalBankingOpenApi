package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.entities.Customer;
import com.mob.casestudy.digitalbanking.exception.CustomersNotFoundException;
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
class CustomerDeleteServiceTest {
    @InjectMocks
    CustomerDeleteService customerDeleteService;
    @Mock
    CustomerRepository customerRepository;

    @Test
    void validateCustomer_CustomerNotFound() {
        String userName = null;
        customerRepository.findByUserName(userName);
        Assertions.assertThrows(CustomersNotFoundException.class, () -> customerDeleteService.validateCustomer(userName));
    }

    @Test
    void validateCustomer_CustomerFound_WithUserName() {
        String userName = "Avada123";
        Customer customer = new Customer();
        Mockito.when(customerRepository.findByUserName(userName)).thenReturn(Optional.of(customer));
        customerDeleteService.validateCustomer(userName);
        Mockito.verify(customerRepository).delete(customer);
    }
}