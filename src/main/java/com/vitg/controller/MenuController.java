package com.vitg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitg.dto.CourseDTO;
import com.vitg.dto.MenuDTO;
import com.vitg.dto.MenuItemChildDTO;
import com.vitg.dto.MenuItemDTO;
import com.vitg.dto.SubCourseDTO;
import com.vitg.repository.CourseRepository;
import com.vitg.service.CourseService;
import com.vitg.service.SubCourseService;

@RestController
@CrossOrigin
@RequestMapping("/api/menu")
public class MenuController {
	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private SubCourseService subCourseService;


	@GetMapping("/menuitems")
	public List<MenuDTO> getMenuItems() {
		List<MenuDTO> menuDTOList = new ArrayList();

		List<CourseDTO> courseList= courseService.getAllCourses();

		for(CourseDTO courseDTO:courseList) {
			MenuDTO menuDTO= new MenuDTO();

			List<SubCourseDTO> subCourseDTOResponse = subCourseService.getSubCourseListByCourseId(courseDTO.getId());
			menuDTO.setCourseId(courseDTO.getId());

			menuDTO.setCourseName(courseDTO.getName());

			menuDTO.setSubCourseList(subCourseDTOResponse);
			menuDTOList.add(menuDTO);
		}
		return menuDTOList;
	}


	@GetMapping("/allMenuitems")
	public List<MenuItemDTO> getAllMenuItems() {

		MenuItemDTO menuItemDTO =null;
		List<MenuItemDTO> menuItemDTOList=new ArrayList();
		List<MenuItemChildDTO> childList=new ArrayList();
		List<MenuDTO> menuDTOList=new ArrayList();

		List<CourseDTO> courseList= courseService.getAllCourses();

		for(CourseDTO courseDTO:courseList) {
			MenuDTO menuDTO= new MenuDTO();

			List<SubCourseDTO> subCourseDTOResponse = subCourseService.getSubCourseListByCourseId(courseDTO.getId());
			menuDTO.setCourseId(courseDTO.getId());

			menuDTO.setCourseName(courseDTO.getName());

			menuDTO.setSubCourseList(subCourseDTOResponse);
			menuDTOList.add(menuDTO);
		}

		for(MenuDTO menuDTO:menuDTOList) {
			menuItemDTO = new MenuItemDTO();
			menuItemDTO.setDisplayName(menuDTO.getCourseName());

			for(SubCourseDTO subcourseDTO:menuDTO.getSubCourseList()) {
				MenuItemChildDTO menuItemChildDTO= new MenuItemChildDTO();
				menuItemChildDTO.setDisplayName(subcourseDTO.getName());
				childList.add(menuItemChildDTO);
			}
			menuItemDTO.setChildren(childList);
			menuItemDTOList.add(menuItemDTO);
		}


		return menuItemDTOList;
	}

}
