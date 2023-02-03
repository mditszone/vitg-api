package com.vitg.serviceimpl;

import java.util.ArrayList;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitg.dto.ResponseDTO;
import com.vitg.dto.StudentSubCourseDTO;
import com.vitg.dto.SubCourseDTO;
import com.vitg.entity.Student;
import com.vitg.entity.StudentSubCourse;
import com.vitg.entity.SubCourse;
import com.vitg.repository.StudentRepository;
import com.vitg.repository.StudentSubCourseRepository;
import com.vitg.repository.SubCourseRepository;
import com.vitg.service.StudentSubCourseService;

@Service
public class StudentSubCourseImpl implements StudentSubCourseService{

	@Autowired
	public StudentSubCourseRepository studentSubCourseRepository;
	
	@Autowired
	public StudentRepository studentRepository;
	
	@Autowired
	public SubCourseRepository subCourseRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<StudentSubCourseDTO> getStudentIdBySubCourseId(int subCourseId) {
		List<StudentSubCourse> studentSubCourseList = studentSubCourseRepository.findAll();
		List<StudentSubCourseDTO> studentSubCourseDTOList = new ArrayList<>();
		
		for (StudentSubCourse selected: studentSubCourseList) {
			if (selected.getSubCourse().getId() == subCourseId) {
				StudentSubCourseDTO dto = modelMapper.map(selected, StudentSubCourseDTO.class);
				studentSubCourseDTOList.add(dto);
			}
		}
		return studentSubCourseDTOList;
	}

	@Override
	public ResponseDTO updateSelectedSubCourses(List<StudentSubCourseDTO> selectedSubCourses) {
		for(StudentSubCourseDTO selected: selectedSubCourses) {
			StudentSubCourse studentSubCourse = new StudentSubCourse();
			Student student = studentRepository.findById(selected.getStudentId());
			SubCourse subCourse = subCourseRepository.findById(selected.getSubCourseId());
			studentSubCourse.setStudent(student);
			studentSubCourse.setSubCourse(subCourse);
			studentSubCourseRepository.save(studentSubCourse);
		}
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage("selected subCourses updated");
		
		return responseDTO;
	}

	@Override
	public List<StudentSubCourseDTO> getSubCoursesByStudentId(int studentId) {
		List<StudentSubCourse> subCourseList = studentSubCourseRepository.findAll();
		List<StudentSubCourseDTO> subCourseDTOList = new ArrayList<>();
		for (StudentSubCourse selected: subCourseList) {
			if (selected.getStudent().getId() == studentId) {
				StudentSubCourseDTO dto = modelMapper.map(selected, StudentSubCourseDTO.class);
				subCourseDTOList.add(dto);
			}
		}
		return subCourseDTOList;
	}

//	@Override
//	public SubCourseDTO getSubCoursesByStudentIdAndSubCourseId(int studentId, int subCourseId) {
//		StudentSubCourse studentSubCourse = studentSubCourseRepository.findByStudentIdAndSubCourseId(studentId, subCourseId);
//		if (studentSubCourse == null) {
//			return null;
//		} else {
//			SubCourse subCourse = subCourseRepository.findById(studentSubCourse.getSubCourse().getId());
//			return modelMapper.map(subCourse, SubCourseDTO.class);
//		}
//		
//	}
	
	
	@Override
	public StudentSubCourseDTO getSubCoursesByStudentIdAndSubCourseId(int studentId, int subCourseId) {
		StudentSubCourse studentSubCourse = studentSubCourseRepository.findByStudentIdAndSubCourseId(studentId, subCourseId);
		if (studentSubCourse == null) {
			return null;
		} else {
			StudentSubCourse studentSubCourseResponse = studentSubCourseRepository.findById(subCourseId);
			return modelMapper.map(studentSubCourseResponse, StudentSubCourseDTO.class);
		}
	}

}
