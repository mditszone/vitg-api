package com.vitg.dto;

import lombok.Data;

@Data
public class FacultyTopicListResponseDTO {

	int topicId;
	String topicName;
	int subCourseId;
	int facultyId;

	public FacultyTopicListResponseDTO(int topicId,String topicName,int subCourseId,int facultyId) {
		this.topicId=topicId;
		this.topicName = topicName;
		this.subCourseId = subCourseId;
		this.facultyId = facultyId;
	}
}
