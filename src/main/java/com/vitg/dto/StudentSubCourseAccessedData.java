package com.vitg.dto;

import java.util.List;

import lombok.Data;

@Data
public class StudentSubCourseAccessedData {
	String topic;  
	List<StudentSubTopicData> subTopicNameList;
}
