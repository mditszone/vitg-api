package com.vitg.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class LockedException extends RuntimeException {
    public LockedException(String message)
    {
    	super(message);
    }
}
