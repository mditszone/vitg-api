package com.vitg.controller;

import java.util.List;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitg.dto.StudentDTO;
import com.vitg.entity.User;
import com.vitg.exception.BadRequestException;
import com.vitg.repository.StudentRepository;
import com.vitg.repository.UserRepository;
import com.vitg.service.StudentService;

@RestController
@CrossOrigin
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentRepository studentRepository;
	

	// Get StudentById
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable(value = "id") int id) { 
		StudentDTO studentDTO = studentService.getStudentById(id);
		return new ResponseEntity<>(studentDTO, HttpStatus.OK);
	} 
	
	// Update Student
	
	@PutMapping("/editStudent")
	public ResponseEntity<StudentDTO> updateStudentInfo(@RequestBody @Valid StudentDTO studentDTO) throws BadRequestException {

		if (!studentRepository.existsById(studentDTO.getId())) {
			throw new BadRequestException("Failed to update StudentInfo ");
		}
		
		User user = userRepository.findByPhoneNumber(studentDTO.getPhoneNumber());
		userRepository.save(user);
		
		studentDTO.setRegistrationStatus(true);
		studentDTO = studentService.updateStudent(studentDTO);

		return new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}
	
	// Get AllStudent
	
	@GetMapping("/getAllStudents")
	public List<StudentDTO> getAllStudents() {
		return studentService.getAllStudents();
	}
}
