
package com.vitg.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vitg.entity.Batch;



@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer>{

	Batch findById(int id);	
	Batch findByName(String name);

	@Query(value ="SELECT * FROM vitgdb.batch" ,nativeQuery = true)
	//List<Batch> getOrganizerList();
	List<Batch>getBatcheslist();
}

