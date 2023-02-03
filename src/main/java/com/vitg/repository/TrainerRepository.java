package com.vitg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vitg.entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

	Trainer findById(int id);
	Trainer findByPhoneNumber(String phoneNumber);
	Trainer findByName(String name);
	
	@Query(value ="SELECT * FROM vitgdb.trainer" ,nativeQuery = true)
	List<Trainer> getTrainerList();
}
