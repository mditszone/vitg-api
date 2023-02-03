package com.vitg.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vitg.entity.StudentSubCourse;

@Repository
public interface StudentSubCourseRepository extends JpaRepository<StudentSubCourse, Integer>{

	public StudentSubCourse findById(int id);

	//	@Query("select * from student_subCourse_selected where student_id=:studentId")
	//	List<StudentSubCourse> findByStudentId(@Param("studentId") int studentId);
	//
	//	@Query("delete from student_subCourse_selected where :student_id in (select * from where student_id= :student_id")
	//	void deleteAllByStudentId(@Param("studentId") int studentId);

	@Query(value= "select topic.id,topic.name, student_subcourse.sub_course_id, student_subcourse.student_id\n"
			+ "from vitgdb.student_subcourse\n"
			+ "INNER JOIN vitgdb.topic ON vitgdb.student_subcourse.sub_course_id = vitgdb.topic.subcourse_id\n"
			+ "WHERE vitgdb.student_subcourse.student_id =:studentId AND vitgdb.student_subcourse.sub_course_id=:subCourseId " ,nativeQuery = true)
	
	List<Map<String, Object>> findTopicListByStudentId(@Param("studentId") int studentId,@Param("subCourseId") int subCourseId);

	public StudentSubCourse findByStudentIdAndSubCourseId(int studentId, int subCourseId);
}
