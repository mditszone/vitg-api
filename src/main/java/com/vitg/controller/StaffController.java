package com.vitg.controller;

import java.util.List;




import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitg.dto.StaffDTO;
import com.vitg.entity.Role;
import com.vitg.entity.Staff;
import com.vitg.entity.User;
import com.vitg.exception.BadRequestException;
import com.vitg.repository.RoleRepository;
import com.vitg.repository.StaffRepository;
import com.vitg.repository.UserRepository;
import com.vitg.service.OTPService;
import com.vitg.service.StaffService;

@RestController
@CrossOrigin
@RequestMapping("/api/staff")
public class StaffController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private OTPService otpService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	
	// Get StaffById
	
	@GetMapping("/{id}")
	public ResponseEntity<StaffDTO> getStaffById(@PathVariable(value = "id") int id) { 
		StaffDTO staffDTO = staffService.getStaffById(id);
		return new ResponseEntity<>(staffDTO, HttpStatus.OK);
	} 

	
//	@SuppressWarnings("rawtypes")
//	@GetMapping("/{delete_id}")
//	public ResponseEntity deleteById(@PathVariable(value = "id") int id) {
//		 Staff staff = staffService.deleteById(id);
//		return ResponseEntity.ok().build();
//		
//	} 
	
	// Update Staff
	
	@PutMapping("/editStaff")
	public ResponseEntity<StaffDTO> updateStaffInfo(@RequestBody @Valid StaffDTO staffDTO) throws BadRequestException {
		if (!staffRepository.existsById(staffDTO.getId())) {
			throw new BadRequestException("Failed to update StaffInfo ");
		}

		User user = userRepository.findByPhoneNumber(staffDTO.getPhoneNumber());
		Role role = roleRepository.findByroleName(staffDTO.getRole().getRoleName());
		user.setRole(role);
		userRepository.save(user);
		
		staffDTO.setRegistrationStatus(true);
		staffDTO = staffService.updateStaff(staffDTO);
		return new ResponseEntity<>(staffDTO, HttpStatus.OK);
	}

	// Get AllStaff
	
	@GetMapping("/getAllStaff")
	public List<StaffDTO> getAllStaff() {
		return staffService.getAllStaff();
	}

//	@GetMapping("/sendNotification")
//	public ResponseEntity<StaffDTO> sendNotfication(@RequestParam String deviceToken, @RequestParam String requestId) {
//		System.out.println(deviceToken);
//		System.out.println(requestId);
//		Staff staff = staffRepository.findByDeviceToken(deviceToken);
//		StaffDTO staffDTO = modelMapper.map(staff, StaffDTO.class);
//		JSONObject json = new JSONObject();
//		json.put("id", requestId);
//		json.put("screen", "mechanic_accept");
//		awsSesService.pushNotification(Integer.parseInt(requestId), "mechanic_accept", staffDTO.getDeviceToken());
//		return new ResponseEntity<>(staffDTO, HttpStatus.OK);
//	}
	
	
//	@PostMapping("/image-upload")
//    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {
//         
//		String fileNameWithOutExt = Files.getNameWithoutExtension(multipartFile.getOriginalFilename());
//		System.out.print(fileNameWithOutExt);
//		byte[] bytes = multipartFile.getBytes();
//
//		StaffDTO staffDTO = staffService.getStaffById(Integer.parseInt(fileNameWithOutExt));
//		staffDTO.setImage(bytes);
//		staffDTO = staffService.updateStaff(staffDTO);
//		
//		return ResponseEntity.status(HttpStatus.OK).body("file uploaded sucessfully");
//	}
	
	/*
	 * @PostMapping("/upload/image") public ResponseEntity<String>
	 * uplaodImage(@RequestParam("image") MultipartFile file) throws IOException {
	 * 
	 * Staff staff = staffRepository.findById(1); staff.setImage(file.getBytes());
	 * staffRepository.save(staff);
	 * 
	 * imageRepository.save(Image.builder() .name(file.getOriginalFilename())
	 * .type(file.getContentType())
	 * .image(ImageUtility.compressImage(file.getBytes())).build()); return
	 * ResponseEntity.status(HttpStatus.OK) .body(new
	 * ImageUploadResponse("Image uploaded successfully: " +
	 * file.getOriginalFilename())); }
	 */

}
