package com.mob.casestudy.digitalbanking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OtpFailedAttemptsException extends RuntimeException {
    public OtpFailedAttemptsException(String message) {
        super(message);
    }
}
