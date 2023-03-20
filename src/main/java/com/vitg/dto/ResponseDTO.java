package com.vitg.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO {

	String message;
	int status;
	List<String> failedEmailList;

	Object data;
}
