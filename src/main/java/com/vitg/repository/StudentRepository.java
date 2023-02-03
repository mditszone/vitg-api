package com.vitg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitg.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	Student findById(int id);
	Student findByPhoneNumber(String phoneNumber);
}
