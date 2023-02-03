package com.vitg.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vitg.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
	Course findById(int id);	
	Course findByName(String name);
	@Query(value ="SELECT * FROM vitgdb.course" ,nativeQuery = true)
	List<Course> getCoursesList();

}





