package com.vitg.dto;

import lombok.Data;

@Data
public class StudentTopicListResponseDTO {
	int topicId;
	String topicName;
	int subCourseId;
	int studentId;

	public StudentTopicListResponseDTO(int topicId,String topicName,int subCourseId,int studentId) {
		this.topicId=topicId;
		this.topicName = topicName;
		this.subCourseId = subCourseId;
		this.studentId = studentId;
	}
}
