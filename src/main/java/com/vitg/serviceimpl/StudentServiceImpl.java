package com.vitg.serviceimpl;

import java.util.ArrayList;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitg.dto.StudentDTO;
import com.vitg.entity.Role;
import com.vitg.entity.Student;
import com.vitg.entity.User;
import com.vitg.mappers.StudentMapper;
import com.vitg.repository.StudentRepository;
import com.vitg.repository.UserRepository;
import com.vitg.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StudentMapper studentMapper;


	public StudentDTO getStudentById(int id) {

		Student student=studentRepository.findById(id);

		StudentDTO studentDTO = studentMapper.studentToStudentDTO(student);

		//Optional<User> user=userRepository.findById(id);

		return studentDTO;

	}

	public void deleteById(int id) {
		studentRepository.deleteById(id);
	}

	public List<StudentDTO> getAllStudents(){
		List<Student>  studentList=studentRepository.findAll();

		List<StudentDTO> studentDTOList=new ArrayList<>();

		for(Student student:studentList) {
			StudentDTO studentDTO=modelMapper.map(student, StudentDTO.class);
			studentDTOList.add(studentDTO);
		}

		return studentDTOList;
	}

	public StudentDTO updateStudent(StudentDTO studentDTO) {
		Student student=modelMapper.map(studentDTO, Student.class);
		Student studentResponse=studentRepository.save(student);
		StudentDTO studentDTOResponse=modelMapper.map(studentResponse, StudentDTO.class);
		return studentDTOResponse;

	}

	@Override
	public StudentDTO findByPhoneNumber(String phoneNumber) {
		Student student = studentRepository.findByPhoneNumber(phoneNumber);
		return modelMapper.map(student, StudentDTO.class);
	}

}
