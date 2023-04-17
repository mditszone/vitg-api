package com.vitg.dto;

import java.util.List;

import com.vitg.controller.FacultySubTopicData;

import lombok.Data;
@Data
public class FacultySubCourseAccessedData {

	String topic;  
	List<FacultySubTopicData> subTopicNameList;
}
