package com.vitg.dto;
import lombok.Data;

@Data
public class CourseDTO {
	private int id;
	private String name;
	private byte[] image;
	private boolean status;
	private String description;
}