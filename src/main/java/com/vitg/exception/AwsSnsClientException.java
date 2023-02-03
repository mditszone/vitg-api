package com.vitg.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AwsSnsClientException extends RuntimeException {

    public AwsSnsClientException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

	public AwsSnsClientException(String message) {
        super(message);
    }
}
