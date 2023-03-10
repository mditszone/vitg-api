
package com.vitg.service;

import java.util.List;

import com.vitg.dto.StudentTopicListResponseDTO;
import com.vitg.dto.SubCourseDTO;
import com.vitg.dto.TopicDTO;


public interface TopicService {
	public TopicDTO getTopicById(int id);
	public void deleteById(int id);
	public TopicDTO createTopic(TopicDTO TopicDTO);
	public TopicDTO updateTopic (TopicDTO TopicDTO);
	public List<TopicDTO> getAllTopics();
	public List<TopicDTO> getTopicListBySubCourseId(int subCourseId);
	//public List<TopicDTO> getTopicListByStudentIdSubCourseId(int studentId,int subCourseId);
	public List<StudentTopicListResponseDTO> getTopicListByStudentId(int studentId,int subCourseId);

}