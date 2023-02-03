package com.vitg.service;

import java.util.List;


import com.vitg.dto.StudentDTO;

public interface StudentService {
	
	public StudentDTO getStudentById(int id);
	public void deleteById(int id);
	public StudentDTO updateStudent(StudentDTO studentDTO);
	public List<StudentDTO> getAllStudents();
	public StudentDTO findByPhoneNumber(String phoneNumber);
}
