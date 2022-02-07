package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.dto.CustomerOtpDto;
import com.mob.casestudy.digitalbanking.service.CustomerOtpValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer-service")
public class CustomerOtpController {
    private final CustomerOtpValidationService customerOtpValidationService;
    @Autowired
    public CustomerOtpController( CustomerOtpValidationService customerOtpValidationService) {
        this.customerOtpValidationService = customerOtpValidationService;
    }

    @PostMapping("/service-api/v2/otp/validate")
    public ResponseEntity<Object> customerOtpValidation(@RequestBody CustomerOtpDto customerOtpDto) {
        customerOtpValidationService.validateCustomerOtp(customerOtpDto);
        return ResponseEntity.ok().build();
    }
}
