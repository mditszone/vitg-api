package com.vitg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vitg.entity.SubTopicConcept;
import com.vitg.entity.Topic;

public interface SubTopicConceptRepository extends JpaRepository<SubTopicConcept, Integer>{
	SubTopicConcept findById(int id);
	@Query(value ="SELECT * FROM vitgdb.subTopicConcept" ,nativeQuery = true)
	List<SubTopicConcept> getSubTopicConceptList();
	
}





