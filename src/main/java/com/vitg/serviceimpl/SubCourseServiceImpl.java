package com.vitg.serviceimpl;

import java.util.ArrayList;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.vitg.dto.CourseDTO;
import com.vitg.dto.SubCourseDTO;
import com.vitg.entity.Course;
import com.vitg.entity.SubCourse;
import com.vitg.repository.CourseRepository;
import com.vitg.repository.SubCourseRepository;
import com.vitg.service.SubCourseService;


@Service
@CrossOrigin
public  class SubCourseServiceImpl implements  SubCourseService{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SubCourseRepository subCourseRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Transactional
	public  SubCourseDTO createSubCourse( SubCourseDTO  subCourseDTO) {
		courseRepository.findById(subCourseDTO.getCourse().getId());
		SubCourse  subCourse = modelMapper.map(subCourseDTO,SubCourse.class);
		SubCourse savedSubCourse =  subCourseRepository.save(subCourse);
		SubCourseDTO  subCourseDTOResponse = modelMapper.map(savedSubCourse,SubCourseDTO.class);
		return  subCourseDTOResponse;
	}

	public SubCourseDTO getSubCourseById(int id) {
		SubCourse subCourse=subCourseRepository.findById(id);
		SubCourseDTO subCourseDTO = modelMapper.map(subCourse, SubCourseDTO.class);
		return subCourseDTO;
	}

	public List<SubCourseDTO> getAllSubCourses() {
		List<SubCourse>  subCourseList=subCourseRepository.findAll();
		List<SubCourseDTO> subCourseDTOList=new ArrayList<>();
		for(SubCourse subCourse:subCourseList) {
			System.out.println(subCourse.getId());
			SubCourseDTO subCourseDTO=modelMapper.map(subCourse, SubCourseDTO.class);
			subCourseDTOList.add(subCourseDTO);
		}
		return subCourseDTOList;
	}

	public SubCourseDTO  updateSubCourse(SubCourseDTO subCourseDTO) {
		SubCourse  subCourse = modelMapper.map( subCourseDTO,SubCourse.class);
		SubCourse  subCourseResponse = subCourseRepository.save( subCourse);
		SubCourseDTO  subCourseDTOResponse = modelMapper.map( subCourseResponse,SubCourseDTO.class);
		return  subCourseDTOResponse;
	}

	public SubCourseDTO saveSubCourse(SubCourseDTO subCourseDTO) {
		SubCourse subCourse=modelMapper.map(subCourseDTO, SubCourse.class);
		SubCourse SubCourseResponse=subCourseRepository.save(subCourse);
		SubCourseDTO subcourseDTOResponse=modelMapper.map(SubCourseResponse, SubCourseDTO.class);
		return subcourseDTOResponse;
	}

	public void deleteById(int id) {
		subCourseRepository.deleteById(id);
	}


	/*public List<SubCourseDTO> getSubCourseListByCourseId(int courseId) {
		List<SubCourse> subCourseList = subCourseRepository.getSubCoursesListByCourseId(courseId);

		List<SubCourseDTO> subCourseDTOList = new ArrayList<>();

		for (SubCourse subCourse: subCourseList) {
			SubCourseDTO subcourseDTO = modelMapper.map(subCourse, SubCourseDTO.class);
			subCourseDTOList.add(subcourseDTO);
		}

		return subCourseDTOList;
	}*/


	@Override
	public List<SubCourseDTO> getSubCourseListByCourseId(int courseId) {
		List<SubCourse> subCourseList = subCourseRepository.findAll();

		List<SubCourseDTO> subCourseDTOList = new ArrayList<>();

		for (SubCourse subCourse: subCourseList) {
			if (subCourse.getCourse().getId() == courseId) {
				SubCourseDTO subcourseDTO = modelMapper.map(subCourse, SubCourseDTO.class);
				subCourseDTOList.add(subcourseDTO);
			}
		}
		return subCourseDTOList;
	}
}