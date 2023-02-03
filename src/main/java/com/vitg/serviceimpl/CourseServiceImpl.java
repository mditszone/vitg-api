
package com.vitg.serviceimpl;


import java.util.ArrayList;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitg.dto.CourseDTO;
import com.vitg.entity.Course;
import com.vitg.repository.CourseRepository;
import com.vitg.service.CourseService;
import com.vitg.serviceimpl.CourseServiceImpl;


@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CourseRepository courseRepository;


	@Transactional
	public CourseDTO createCourse(CourseDTO courseDTO) {
		Course course = modelMapper.map(courseDTO, Course.class);
		Course savedCourse = courseRepository.save(course);
		CourseDTO courseDTOResponse=modelMapper.map(savedCourse, CourseDTO.class);
		return courseDTOResponse;
	}

	public CourseDTO getCourseById(int id) {
		Course course = courseRepository.findById(id);
		CourseDTO courseDTO = modelMapper.map(course, CourseDTO.class);
		return courseDTO;

	}

	public List<CourseDTO> getAllCourses() {
		List<Course>  courseList=courseRepository.findAll();
		List<CourseDTO> courseDTOList=new ArrayList<>();
		for(Course course:courseList) {
			CourseDTO courseDTO=modelMapper.map(course, CourseDTO.class);
			courseDTOList.add(courseDTO);
		}
		return courseDTOList;
	}

	public CourseDTO updateCourse(CourseDTO courseDTO) {
		//courseDTO.setStatus(true);
		Course course = modelMapper.map(courseDTO, Course.class);
		Course courseResponse = courseRepository.save(course);
		CourseDTO courseDTOResponse = modelMapper.map(courseResponse, CourseDTO.class);
		return courseDTOResponse;
	}

	@Override
	public void deleteById(int id) {
		courseRepository.deleteById(id);
	}	

}