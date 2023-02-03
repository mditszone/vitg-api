package com.vitg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vitg.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
	
	 
	 Role findByroleName(String roleName);
	 @Query(value ="SELECT * FROM vitgdb.role where role_name NOT IN ('STUDENT')" ,nativeQuery = true)
	 List<Role> getStaffRoles();
}
