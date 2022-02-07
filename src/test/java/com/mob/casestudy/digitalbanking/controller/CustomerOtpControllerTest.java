package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.dto.CustomerOtpDto;
import com.mob.casestudy.digitalbanking.service.CustomerOtpValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CustomerOtpControllerTest {
    @InjectMocks
    CustomerOtpController customerOtpController;
    @Mock
    CustomerOtpValidationService customerOtpValidationService;

    @Test
    void validateCustomerOtp() {
        CustomerOtpDto customerOtpDto = new CustomerOtpDto();
        customerOtpValidationService.validateCustomerOtp(customerOtpDto);
        ResponseEntity<Object> build = ResponseEntity.ok().build();
        ResponseEntity<Object> objectResponseEntity = customerOtpController.customerOtpValidation(customerOtpDto);
        Assertions.assertEquals(build, objectResponseEntity);
        Mockito.verify(customerOtpValidationService, Mockito.times(2)).validateCustomerOtp(customerOtpDto);
    }
}