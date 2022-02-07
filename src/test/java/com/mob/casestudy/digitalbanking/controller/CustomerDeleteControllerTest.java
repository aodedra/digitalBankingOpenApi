package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.service.CustomerDeleteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CustomerDeleteControllerTest {
    @InjectMocks
    CustomerDeleteController customerDeleteController;
    @Mock
    CustomerDeleteService customerDeleteService;

    @Test
    void deleteCustomerByUserName() {
        String userName="Avada12";
        customerDeleteService.validateCustomer(userName);
        ResponseEntity<Object> build = ResponseEntity.noContent().build();
        ResponseEntity<Object> objectResponseEntity = customerDeleteController.deleteCustomerByUserName(userName);
        Assertions.assertEquals(build, objectResponseEntity);
        Mockito.verify(customerDeleteService,Mockito.times(2)).validateCustomer(userName);
    }
}