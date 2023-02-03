
package com.vitg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vitg.entity.SubTopic;

@Repository
public interface SubTopicRepository extends JpaRepository<SubTopic, Integer>{
	SubTopic findById(int id);
	SubTopic findByName(String name);
	@Query(value ="SELECT * FROM vitgdb.subTopic" ,nativeQuery = true)
	List<SubTopic> getSubTopicList();

}