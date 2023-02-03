package com.vitg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitg.entity.Slider;

@Repository
public interface SliderRepository extends JpaRepository<Slider, Integer> {

	Slider findById(int id);
	Slider findByName(String name);
}
;