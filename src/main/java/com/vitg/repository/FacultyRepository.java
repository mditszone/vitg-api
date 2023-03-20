package com.vitg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitg.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer>{

	Faculty findById(int id);
	
}
