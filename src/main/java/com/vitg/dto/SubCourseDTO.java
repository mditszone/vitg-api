package com.vitg.dto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id","name","image","course","durationDays","durationHours","youtubeUrl","fee","overview","curriculum","examCertification","trainingMode",})
public class SubCourseDTO {
	private int id;	
	private String name;
    private byte[] image;
	private int durationDays;
	private int durationHours;
	private String youtubeUrl;
	private int fee;
	private String overview;
	private String curriculum;
	private String examCertification;
	private String trainingMode;
	private CourseDTO course;
}
