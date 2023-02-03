
package com.vitg.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vitg.entity.SubCourse;

@Repository
public interface SubCourseRepository extends JpaRepository<SubCourse, Integer>{

	SubCourse findById(int id);
	SubCourse findByName(String name);

	@Query(value ="SELECT * FROM vitgdb.subCourse" ,nativeQuery = true)
	List<SubCourse> getSubCoursesList();

}
