package com.vitg.serviceimpl;

import java.util.ArrayList;



import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitg.dto.StaffDTO;
import com.vitg.entity.Role;
import com.vitg.entity.Staff;
import com.vitg.entity.User;
import com.vitg.repository.StaffRepository;
import com.vitg.repository.UserRepository;
import com.vitg.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private UserRepository userRepository;

	public StaffDTO getStaffById(int id) {
		Staff staff = staffRepository.findById(id);
		StaffDTO staffDTO = modelMapper.map(staff, StaffDTO.class);
		return staffDTO;
	}

	public List<StaffDTO> getAllStaff(){
		List<Staff>  staffList = staffRepository.findAll();
		List<StaffDTO> staffDTOList = new ArrayList<>();
		for(Staff staff:staffList) {
			StaffDTO staffDTO = modelMapper.map(staff, StaffDTO.class);
			staffDTOList.add(staffDTO);
		}
		return staffDTOList;
	}

	@Override
	public List<StaffDTO> getAllOrganizers() {
		System.out.println("test1");
		return staffRepository.findAll()
				.stream()
				.filter(item -> item.getRole().getId() == 4)
				.map(item -> modelMapper.map(item, StaffDTO.class))
				.collect(Collectors.toList());
	}

	public StaffDTO updateStaff(StaffDTO staffDTO) {
		Staff staff = modelMapper.map(staffDTO, Staff.class);
		Staff staffResponse = staffRepository.save(staff);
		Role role=staffResponse.getRole();
		User user=userRepository.findByPhoneNumber(staff.getPhoneNumber());
		user.setRole(role);
		userRepository.save(user);
		StaffDTO staffDTOResponse = modelMapper.map(staffResponse, StaffDTO.class);
		return staffDTOResponse;

	}

}
