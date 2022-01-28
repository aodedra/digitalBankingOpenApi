package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.dto.CustomerOtpDto;
import com.mob.casestudy.digitalbanking.service.OtpValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerOtpController {



    private final OtpValidationService otpValidationService;

    public CustomerOtpController( OtpValidationService otpValidationService) {

        this.otpValidationService = otpValidationService;
    }

    @PostMapping("/service-api/v2/otp/validate")
    public ResponseEntity<Object> validateCustomerOtp(@RequestBody CustomerOtpDto customerOtpDto) {
        otpValidationService.validateCustomerOtp(customerOtpDto);
        return ResponseEntity.ok().build();

    }
}
