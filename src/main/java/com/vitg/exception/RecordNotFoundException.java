package com.vitg.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -4014640882187769548L;

	public RecordNotFoundException(String exception) {
		super(exception);
	}
}
