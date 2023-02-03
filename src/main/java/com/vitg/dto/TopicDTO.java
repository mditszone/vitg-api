package com.vitg.dto;
import lombok.Data;

@Data
public class TopicDTO {
	private int id;	
	private String name;
	private SubCourseDTO subCourse;
}
