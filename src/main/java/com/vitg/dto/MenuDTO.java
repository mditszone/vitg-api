package com.vitg.dto;

import java.util.List;

import lombok.Data;

@Data
public class MenuDTO {
	
	
	private int courseId;
	private String courseName;
	
	List<SubCourseDTO> subCourseList;

}
