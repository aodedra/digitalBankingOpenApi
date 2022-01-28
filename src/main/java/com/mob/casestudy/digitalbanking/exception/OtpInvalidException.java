package com.mob.casestudy.digitalbanking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OtpInvalidException extends RuntimeException {
    public OtpInvalidException(String message) {
        super(message);
    }
}
