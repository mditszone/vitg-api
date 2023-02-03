package com.vitg.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitg.dto.BatchDTO;
import com.vitg.dto.CourseDTO;
import com.vitg.dto.RoleDTO;
import com.vitg.dto.SubCourseDTO;
import com.vitg.dto.SubTopicDTO;
import com.vitg.dto.TopicDTO;
import com.vitg.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/getAllRoles")
	public List<RoleDTO> getAllRoles() {
		return adminService.getAllRoles();
	}
	
	@GetMapping("/getStaffRoles")
	public List<RoleDTO> getStaffRoles() {
		return adminService.getStaffRoles();
	}
	
	@GetMapping("/getCoursesList")
	public List<CourseDTO> getCoursesList() {
		return adminService.getCoursesList();
	}
	
	@GetMapping("/getSubCoursesList")
	public List<SubCourseDTO> getSubCoursesList() {
		return adminService.getSubCoursesList();
	}
	
	@GetMapping("/getTopicList")
	public List<TopicDTO> getTopicList() {
		return adminService.getTopicList();
	}
	
	@GetMapping("/getSubTopicList")
	public List<SubTopicDTO> getSubTopicList() {
		return adminService.getSubTopicList();
	}
	@GetMapping("/getBatchesList")
	public List<BatchDTO> getBatchesList() {
		return adminService.getBatchesList();
	}
}
