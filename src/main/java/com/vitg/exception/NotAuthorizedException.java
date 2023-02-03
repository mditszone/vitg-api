package com.vitg.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(String message)
    {
    	super(message);
    }
}