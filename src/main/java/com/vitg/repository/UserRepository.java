package com.vitg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vitg.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByPhoneNumber(String phoneNumber);
	Optional<User> findById(int id);

	boolean existsByPhoneNumber(String phonenumber);

	   @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.phoneNumber = ?2")
	    @Modifying
	    public void updateFailedAttempts(int failAttempts, String phoneNumber);

}
