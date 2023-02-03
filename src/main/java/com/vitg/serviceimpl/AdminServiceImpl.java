package com.vitg.serviceimpl;

import java.util.ArrayList;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitg.dto.BatchDTO;
import com.vitg.dto.CourseDTO;
import com.vitg.dto.RoleDTO;
import com.vitg.dto.SubCourseDTO;
import com.vitg.dto.SubTopicDTO;
import com.vitg.dto.TopicDTO;
import com.vitg.entity.Batch;
import com.vitg.entity.Course;
import com.vitg.entity.Role;
import com.vitg.entity.SubCourse;
import com.vitg.entity.SubTopic;
import com.vitg.entity.Topic;
import com.vitg.repository.BatchRepository;
import com.vitg.repository.CourseRepository;
import com.vitg.repository.RoleRepository;
import com.vitg.repository.SubCourseRepository;
import com.vitg.repository.SubTopicRepository;
import com.vitg.repository.TopicRepository;
import com.vitg.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private SubCourseRepository subCourseRepository;
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private SubTopicRepository subTopicRepository;

	public List<RoleDTO> getAllRoles(){
		List<Role>  roleList=roleRepository.findAll();

		List<RoleDTO> roleDTOList=new ArrayList<>();

		for(Role role: roleList) {
			RoleDTO roleDTO=modelMapper.map(role, RoleDTO.class);			
			roleDTOList.add(roleDTO);
		}

		return roleDTOList;
	}
	public List<RoleDTO> getStaffRoles(){
		List<Role>  roleList=roleRepository.getStaffRoles();

		List<RoleDTO> roleDTOList=new ArrayList<>();

		for(Role role:roleList) {
			RoleDTO roleDTO=modelMapper.map(role, RoleDTO.class);
			roleDTOList.add(roleDTO);
		}

		return roleDTOList;
	}
	
	public List<CourseDTO> getCoursesList(){
		List<Course>  courseList=courseRepository.getCoursesList();

		List<CourseDTO> courseDTOList=new ArrayList<>();

		for(Course course:courseList) {
			CourseDTO courseDTO=modelMapper.map(course, CourseDTO.class);
			courseDTOList.add(courseDTO);
		}

		return courseDTOList;
	}
	
	public List<SubCourseDTO> getSubCoursesList(){
		List<SubCourse>  subCourseList=subCourseRepository.getSubCoursesList();

		List<SubCourseDTO> subCourseDTOList=new ArrayList<>();

		for(SubCourse subCourse:subCourseList) {
			SubCourseDTO courseDTO=modelMapper.map(subCourse, SubCourseDTO.class);
			subCourseDTOList.add(courseDTO);
		}

		return subCourseDTOList;
	}
	public List<TopicDTO> getTopicList(){
		List<Topic>  topicList=topicRepository.getTopicList();

		List<TopicDTO> topicDTOList=new ArrayList<>();

		for(Topic topic:topicList) {
			TopicDTO topicDTO=modelMapper.map(topic, TopicDTO.class);
			topicDTOList.add(topicDTO);
		}

		return topicDTOList;
	}
	public List<SubTopicDTO> getSubTopicList(){
		List<SubTopic>  subTopicList=subTopicRepository.getSubTopicList();

		List<SubTopicDTO> subTopicDTOList=new ArrayList<>();

		for(SubTopic subTopic:subTopicList) {
			SubTopicDTO subTopicDTO=modelMapper.map(subTopic, SubTopicDTO.class);
			subTopicDTOList.add(subTopicDTO);
		}

		return subTopicDTOList;
	}
	
	@Override
	public List<BatchDTO> getBatchesList() {
		List<Batch> batchList=batchRepository.getBatcheslist();
		List<BatchDTO> batchDTOList=new ArrayList<>();
		
		for(Batch  batch:batchList) {
			BatchDTO batchDTO=modelMapper.map(batch, BatchDTO.class);
			batchDTOList.add(batchDTO);
		}
		
		
		return batchDTOList;
	}

}
