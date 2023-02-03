package com.vitg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vitg.entity.SubCourse;
import com.vitg.entity.TrainerCourse;

@Repository
public interface TrainerCourseRepository extends JpaRepository<TrainerCourse, Integer> {

	TrainerCourse findById(int id);
	TrainerCourse findByTrainerName(String name);

	@Query(value ="SELECT * FROM vitgdb.trainerCourse" ,nativeQuery = true)
	List<TrainerCourse> getTrainersList();
	
}
