package com.vitg.dto;

import java.util.List;

import lombok.Data;

@Data
public class FacultyDTO {
	private int id;
	private String userName;
	private int pin;
	private List<Integer> facultySubCourseIdList;
	private String role;
}
