package com.vitg.repository;

import java.util.List;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vitg.entity.FacultySubCourse;

@Repository
public interface FacultySubCourseRepository extends JpaRepository<FacultySubCourse,Integer> {

	public FacultySubCourse findById(int id);

	@Query(value= "select topic.id,topic.name, faculty_subcourse.sub_course_id, faculty_subcourse.faculty_id\n"
			+ "from vitgdb.faculty_subcourse\n"
			+ "INNER JOIN vitgdb.topic ON vitgdb.faculty_subcourse.sub_course_id = vitgdb.topic.subcourse_id\n"
			+ "WHERE vitgdb.faculty_subcourse.faculty_id =:facultyId AND vitgdb.faculty_subcourse.sub_course_id=:subCourseId " ,nativeQuery = true)
	
	List<Map<String, Object>> findTopicListByFacultyId(@Param("facultyId") int facultyId,@Param("subCourseId") int subCourseId);

	public FacultySubCourse findByFacultyIdAndSubCourseId(int facultyId, int subCourseId);
}
