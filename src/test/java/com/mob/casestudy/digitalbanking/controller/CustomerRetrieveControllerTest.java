package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.service.CustomerRetrieveService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerRetrieveControllerTest {
    @InjectMocks
    CustomerRetrieveController customerRetrieveController;
    @Mock
    CustomerRetrieveService customerRetrieveService;

    @Test
    void retrieveCustomerByUserNameAndId() {
        String userName = "AVada123";
        UUID id = UUID.randomUUID();
        customerRetrieveService.validateCustomerByIdAndUserName(userName, id.toString());
        ResponseEntity<Object> build = ResponseEntity.ok().body(customerRetrieveService.findCustomerByIdOrUsername(userName,id.toString() ));
        ResponseEntity<Object> objectResponseEntity = customerRetrieveController.retrieveCustomerByUserNameAndId(userName, id.toString());
        Assertions.assertEquals(build, objectResponseEntity);
        Mockito.verify(customerRetrieveService).validateCustomerByIdAndUserName(userName, id.toString());
    }
}