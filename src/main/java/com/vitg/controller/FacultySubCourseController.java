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

import com.vitg.dto.FacultySubCourseDTO;
import com.vitg.dto.ResponseDTO;
import com.vitg.exception.NotAuthorizedException;
import com.vitg.service.FacultySubCourseService;

@RestController
@CrossOrigin
@RequestMapping("/api/facultySubCourse")
public class FacultySubCourseController {

	@Autowired
	private FacultySubCourseService facultySubCourseService;

	@GetMapping("/by_faculty_id_and_subCourse_id")
	public ResponseEntity<List<FacultySubCourseDTO>> getFacultyIdBySubCourseId(@PathVariable(value = "categoryId") int subCourseId) {
		List<FacultySubCourseDTO> facultySubCourseDTOResponse = facultySubCourseService.getFacultyIdBySubCourseId(subCourseId);
		
		return new ResponseEntity<>(facultySubCourseDTOResponse, HttpStatus.OK);
	}

	@PutMapping("/update_selected_subCourses")
	public ResponseEntity<ResponseDTO> updateListofSelectedSubCourses(@RequestBody List<FacultySubCourseDTO> selectedSubCourses){
		ResponseDTO responseDTO = facultySubCourseService.updateSelectedSubCourses(selectedSubCourses);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
	
	
	@GetMapping("/by_subCourse_id_and_faculty_id")
	public ResponseEntity<FacultySubCourseDTO> getFacultyIdBySubCourseId(@RequestParam("facultyId") int facultyId, @RequestParam("subCourseId") int subCourseId) {
		FacultySubCourseDTO facultySubCourseDTO = facultySubCourseService.getSubCoursesByFacultyIdAndSubCourseId(facultyId, subCourseId);
		
		if (facultySubCourseDTO == null) {
			throw new NotAuthorizedException("faculty has no access to material");
		}
		return new ResponseEntity<>(facultySubCourseDTO, HttpStatus.OK);
	}
}
