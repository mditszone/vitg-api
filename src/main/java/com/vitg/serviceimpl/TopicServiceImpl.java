


package com.vitg.serviceimpl;

import java.util.ArrayList;


import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitg.dto.FacultyTopicListResponseDTO;
import com.vitg.dto.StudentTopicListResponseDTO;
import com.vitg.dto.TopicDTO;
import com.vitg.entity.Topic;
import com.vitg.repository.FacultySubCourseRepository;
import com.vitg.repository.StudentSubCourseRepository;
import com.vitg.repository.SubCourseRepository;
import com.vitg.repository.TopicRepository;
import com.vitg.service.TopicService;


@Service
public  class TopicServiceImpl implements TopicService{


	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private  TopicRepository topicRepository;

	@Autowired
	private  SubCourseRepository  subCourseRepository;

	@Autowired
	private StudentSubCourseRepository studentSubCourseRepository;
	
	
	@Autowired
	private FacultySubCourseRepository facultySubCourseRepository;

	@Transactional
	public  TopicDTO createTopic(TopicDTO topicDTO) {

		subCourseRepository.findById(topicDTO.getSubCourse().getId());

		Topic  topic = modelMapper.map(topicDTO,  Topic.class);
		Topic savedTopic =  topicRepository.save( topic);
		TopicDTO  topicDTOResponse = modelMapper.map(savedTopic,  TopicDTO.class);
		return  topicDTOResponse;
	}

	public TopicDTO getTopicById(int id) {
		Topic topic = topicRepository.findById(id);
		TopicDTO topicDTO = modelMapper.map(topic,TopicDTO.class);
		return topicDTO;
	}

	public List<TopicDTO> getAllTopics(){
		List<Topic> topicList = topicRepository.findAll();
		List<TopicDTO> topicDTOList = new ArrayList<>();

		for(Topic topic:topicList) {
			TopicDTO topicDTO = modelMapper.map(topic, TopicDTO.class);
			topicDTOList.add(topicDTO);
		}
		return topicDTOList;
	}

	public TopicDTO updateTopic(TopicDTO topicDTO) {
		Topic topic=(Topic) modelMapper.map(topicDTO, Topic.class);
		Topic topicResponse=topicRepository.save(topic);
		TopicDTO topicDTOResponse=(TopicDTO) modelMapper.map(topicResponse, TopicDTO.class);
		return topicDTOResponse;

	}

	@Override
	public void deleteById(int id) {

		topicRepository.deleteById(id);
	}

	@Override
	public List<TopicDTO> getTopicListBySubCourseId(int subCourseId) {
		List<Topic> topicList = topicRepository.findAll();
		List<TopicDTO> topicDTOList = new ArrayList<>();

		for(Topic topic: topicList) {
			if(topic.getSubCourse().getId() == subCourseId) {
				TopicDTO topicDTO = modelMapper.map(topic, TopicDTO.class);
				topicDTOList.add(topicDTO);
			}
		}
		System.out.println(topicDTOList);
		return topicDTOList;
	}

	@Override
	public List<StudentTopicListResponseDTO> getTopicListByStudentId(int studentId,int subCourseId) {
		List<Map<String, Object>> listOfTopics = studentSubCourseRepository.findTopicListByStudentId(studentId, subCourseId);
		
		List<StudentTopicListResponseDTO> response = new ArrayList<>();
		
		for(Map<String,Object> item : listOfTopics) {
			response.add(new StudentTopicListResponseDTO((int)item.get("id"),(String)item.get("name"),(int)item.get("sub_course_id"), 
					(int)item.get("student_id")));
		}
		return response;
	}
	@Override
	public List<FacultyTopicListResponseDTO> getTopicListByFacultyId(int facultyId,int subCourseId) {
		List<Map<String, Object>> listOfTopics = facultySubCourseRepository.findTopicListByFacultyId(facultyId, subCourseId);
		
		List<FacultyTopicListResponseDTO> response = new ArrayList<>();
		
		for(Map<String,Object> item : listOfTopics) {
			response.add(new FacultyTopicListResponseDTO((int)item.get("id"),(String)item.get("name"),(int)item.get("sub_course_id"), 
					(int)item.get("faculty_id")));
		}
		return response;
	}

}