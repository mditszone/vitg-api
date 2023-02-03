package com.vitg.service;

import java.util.List;

import com.vitg.dto.StaffDTO;

public interface StaffService {

	public StaffDTO getStaffById(int id);
	public StaffDTO updateStaff(StaffDTO staffDTO);
	public List<StaffDTO> getAllStaff();
}
