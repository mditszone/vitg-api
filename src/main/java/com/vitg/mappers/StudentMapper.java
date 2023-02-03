package com.vitg.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitg.dto.StudentDTO;
import com.vitg.entity.Student;

@Component
public class StudentMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public StudentDTO studentToStudentDTO(Student student) {
		return modelMapper.map(student,StudentDTO.class);
	}

	public Student StudentDTOToStudent(StudentDTO studentDTO) {

		return modelMapper.map(studentDTO, Student.class);
	}
}
