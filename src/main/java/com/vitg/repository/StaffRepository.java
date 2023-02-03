package com.vitg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitg.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

	Staff findById(int id);
	Staff findByPhoneNumber(String phoneNumber);
}
