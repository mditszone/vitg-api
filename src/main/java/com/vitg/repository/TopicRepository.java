
package com.vitg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vitg.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer>{
	Topic findById(int id);
	Topic findByName(String name);
	@Query(value ="SELECT * FROM vitgdb.topic" ,nativeQuery = true)
	List<Topic> getTopicList();

}