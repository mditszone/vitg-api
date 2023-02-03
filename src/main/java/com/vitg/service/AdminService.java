package com.vitg.service;

import java.util.List;

import com.vitg.dto.BatchDTO;
import com.vitg.dto.CourseDTO;
import com.vitg.dto.RoleDTO;
import com.vitg.dto.SubCourseDTO;
import com.vitg.dto.SubTopicDTO;
import com.vitg.dto.TopicDTO;

public interface AdminService {

	public List<RoleDTO> getAllRoles();
	public List<RoleDTO> getStaffRoles();
	public List<CourseDTO> getCoursesList();
	public List<SubCourseDTO> getSubCoursesList();
	public List<TopicDTO> getTopicList();
	public List<SubTopicDTO> getSubTopicList();
	public List<BatchDTO> getBatchesList();
}
