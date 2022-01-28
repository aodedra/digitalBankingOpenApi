package com.mob.casestudy.digitalbanking.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException() {
        ExceptionResource exceptionResponse = new ExceptionResource("CSI-GET-FIE-002", "The Requested User Not Found");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerSecurityImageNotFound.class)
    public final ResponseEntity<Object> handleCustomerSecurityImageNotFound() {
        ExceptionResource exceptionResponse = new ExceptionResource("CSI-GET-FIE-001", "The Requested User Security Image Not Found");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<Object> handleCustomerNotFoundException() {
        ExceptionResource exceptionResponse = new ExceptionResource("OTP-VAL-FIE-001", "The Requested Customer Not Found");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OtpInvalidException.class)
    public final ResponseEntity<Object> handleInvalidOtp() {
        ExceptionResource exceptionResponse = new ExceptionResource("OTP-VAL-FIE-003", "Entered Otp is Invalid");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OtpEmptyOrNullException.class)
    public final ResponseEntity<Object> handleNullOrEmptyOtp() {
        ExceptionResource exceptionResponse = new ExceptionResource("OTP-VAL-FIE-002", "Null or Empty Opt not Acceptable");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OtpFailedAttemptsException.class)
    public final ResponseEntity<Object> handleFailedOtpAttempts() {
        ExceptionResource exceptionResponse = new ExceptionResource("OTP-VAL-FIE-004", "The Requested User Otp Attempt not acceptable more than 3 times");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OtpInitiatedExpiredException.class)
    public final ResponseEntity<Object> handleInitiatedExpiredOtp() {
        ExceptionResource exceptionResponse = new ExceptionResource("OTP-VAL-FIE-005", "Entered Otp is Expired");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
