
package com.vitg.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.vitg.dto.CourseDTO;
import com.vitg.exception.BadRequestException;
import com.vitg.exception.RecordNotFoundException;
import com.vitg.repository.CourseRepository;
import com.vitg.service.CourseService;
@RestController
@CrossOrigin
@RequestMapping("/api/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseRepository courseRepository;
	
	@PostMapping()
	public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
		CourseDTO courseDTOResponse = courseService.createCourse(courseDTO);
		return new ResponseEntity<>(courseDTOResponse, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CourseDTO> getCourseById(@PathVariable(value = "id") int id){
		CourseDTO courseDTO = courseService.getCourseById(id);
		return new ResponseEntity<>(courseDTO, HttpStatus.OK);
	}

	@GetMapping("/AllCourses")
	public List<CourseDTO> getAllCourses() {
		return courseService.getAllCourses();
	}
	
	@PutMapping("/editCourse")
	public ResponseEntity<CourseDTO> updateCourseInfo( @RequestBody @Valid CourseDTO courseDTO)
			throws BadRequestException {
		if (!courseRepository.existsById(courseDTO.getId())) {
			throw new RecordNotFoundException("Course details does'nt exist ");
		}
		courseDTO = courseService.updateCourse(courseDTO);
		return new ResponseEntity<>(courseDTO, HttpStatus.OK);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteCourseById(@PathVariable(value = "id") int id) { 
		courseService.deleteById(id);
		return ResponseEntity.ok().build();

	}

}
