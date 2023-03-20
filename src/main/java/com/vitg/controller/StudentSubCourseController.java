package com.vitg.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vitg.dto.ResponseDTO;
import com.vitg.dto.StudentSubCourseDTO;
import com.vitg.dto.SubCourseDTO;
import com.vitg.exception.NotAuthorizedException;
import com.vitg.service.StudentSubCourseService;

@RestController
@CrossOrigin
@RequestMapping("/api/studentSubCourse")
public class StudentSubCourseController {

	@Autowired
	private StudentSubCourseService studentSubCourseService;

	@GetMapping("/by_student_id_and_subCourse_id")
	public ResponseEntity<List<StudentSubCourseDTO>> getStudentIdBySubCourseId(@PathVariable(value = "categoryId") int subCourseId) {
		List<StudentSubCourseDTO> studentSubCourseDTOResponse = studentSubCourseService.getStudentIdBySubCourseId(subCourseId);
		
		return new ResponseEntity<>(studentSubCourseDTOResponse, HttpStatus.OK);
	}

	@PutMapping("/update_selected_subCourses")
	public ResponseEntity<ResponseDTO> updateListofSelectedSubCourses(@RequestBody List<StudentSubCourseDTO> selectedSubCourses){
		ResponseDTO responseDTO = studentSubCourseService.updateSelectedSubCourses(selectedSubCourses);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
	
	@GetMapping("/by_subCourse_id_and_student_id")
	public ResponseEntity<StudentSubCourseDTO> getStudentIdBySubCourseId(@RequestParam("studentId") int studentId, @RequestParam("subCourseId") int subCourseId) {
		StudentSubCourseDTO studentSubCourseDTO = studentSubCourseService.getSubCoursesByStudentIdAndSubCourseId(studentId, subCourseId);
		
		if (studentSubCourseDTO == null) {
			throw new NotAuthorizedException("student has no access to material");
		}
		return new ResponseEntity<>(studentSubCourseDTO, HttpStatus.OK);
	}
	
}
