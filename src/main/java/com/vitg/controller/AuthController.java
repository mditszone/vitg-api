package com.vitg.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vitg.dto.OTPResponseDTO;
import com.vitg.dto.PhoneVerificationDTO;
import com.vitg.dto.RoleDTO;
import com.vitg.dto.StaffDTO;
import com.vitg.dto.StudentDTO;
import com.vitg.dto.StudentSubCourseDTO;
import com.vitg.dto.TrainerDTO;
import com.vitg.dto.UserDTO;
import com.vitg.entity.PhoneVerification;
import com.vitg.entity.User;
import com.vitg.entity.Role;
import com.vitg.entity.Staff;
import com.vitg.entity.Student;
import com.vitg.entity.Trainer;
import com.vitg.exception.AwsSnsClientException;
import com.vitg.exception.LockedException;
import com.vitg.exception.OTPException;
import com.vitg.exception.RecordNotFoundException;
import com.vitg.exception.ResourceAlreadyExistsException;
import com.vitg.repository.RoleRepository;
import com.vitg.repository.StaffRepository;
import com.vitg.repository.StudentRepository;
import com.vitg.repository.TrainerRepository;
import com.vitg.repository.UserRepository;
import com.vitg.service.OTPService;
import com.vitg.service.StaffService;
import com.vitg.service.StudentService;
import com.vitg.service.StudentSubCourseService;
import com.vitg.service.TrainerService;
import com.vitg.service.UserService;
import com.vitg.serviceimpl.UserServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private OTPService otpService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private UserRepository userRepository;

	@Autowired 
	private StaffService staffService;

	@Autowired 
	private TrainerService trainerService;

	@Autowired 
	private StudentSubCourseService studentSubCourseService;

	@Autowired 
	private StudentService studentService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired 
	private StaffRepository staffRepository;

	@Autowired 
	private TrainerRepository trainerRepository;

	@Autowired 
	private StudentRepository studentRepository;

	@Autowired
	private ModelMapper modelMapper;


	//Admin will register staff : Send OTP functionality
	@CrossOrigin
	@GetMapping("register/vitg/staff/sendOtp") 
	public ResponseEntity<OTPResponseDTO> sendOtpForVitgStaffRegistration(@RequestParam String phoneNumber) { 

		if (userRepository.existsByPhoneNumber(phoneNumber)) { 
			throw new ResourceAlreadyExistsException("account with  : " + phoneNumber + " already exists"); 
		}
		else {

			int otp = otpService.generateOTP();
			String verificationRef = otpService.generateVerificationRef();
			otpService.savePhoneVerification(new PhoneVerification(phoneNumber, verificationRef, String.valueOf(otp), 0));
			try {
				otpService.sendOTP(String.valueOf(otp),phoneNumber);

			}catch (AwsSnsClientException e)
			{ 
				throw new AwsSnsClientException("Not able to send SMS to : " + phoneNumber ); 
			}

			OTPResponseDTO otpResponseDTO = new OTPResponseDTO();
			otpResponseDTO.setPhoneNumber(phoneNumber);
			otpResponseDTO.setVerificationRef(verificationRef);
			otpResponseDTO.setStatusMessage("SUCCESS");
			return new ResponseEntity<>(otpResponseDTO, HttpStatus.OK);

		}
	}

	//Admin will verify OTP to register Staff

	@CrossOrigin
	@PostMapping("register/vitg/staff/verifyOtp") 
	public ResponseEntity<StaffDTO> validateStaffOtp(@RequestBody PhoneVerificationDTO phoneVerificationDTO ) {

		boolean verified = otpService.verifyOtp(phoneVerificationDTO);

		if(verified) {

			User user = new User();
			user.setPhoneNumber(phoneVerificationDTO.getPhoneNumber());
			user.setLockTime(null);
			user.setAccountNonLocked(true);
			Role role = roleRepository.findByroleName("GUEST");
			user.setRole(role);
			userService.saveUser(user);

			UserDTO userDTO = userService.saveUser(user);

			StaffDTO staffDTO = new StaffDTO(); 
			staffDTO.setId(userDTO.getId());
			staffDTO.setPhoneNumber(userDTO.getPhoneNumber());
			staffDTO.setRegistrationStatus(false);

			RoleDTO roleDTO = new RoleDTO();
			roleDTO.setId(role.getId());
			roleDTO.setRoleName(role.getRoleName());

			staffDTO.setRole(roleDTO);
			StaffDTO staffDTOResponse = staffService.updateStaff(staffDTO);

			return new ResponseEntity<>(staffDTOResponse, HttpStatus.CREATED);
		}
		else{
			throw new OTPException("Invalid OTP");
		}

	}

	//Admin will register Trainer : Send OTP functionality

	@GetMapping("register/vitg/trainer/sendOtp") 
	public ResponseEntity<OTPResponseDTO> sendOtpForVitgTrainerRegistration(@RequestParam String phoneNumber) {

		if (userRepository.existsByPhoneNumber(phoneNumber)) { 
			throw new ResourceAlreadyExistsException("account with  : " + phoneNumber +
					" already exists"); 
		}

		int otp = otpService.generateOTP();
		String verificationRef = otpService.generateVerificationRef();
		otpService.savePhoneVerification(new PhoneVerification(phoneNumber, verificationRef, String.valueOf(otp), 0));
		try {
			otpService.sendOTP(String.valueOf(otp),phoneNumber);
		}catch (AwsSnsClientException e)
		{ 
			throw new AwsSnsClientException("Not able to send SMS to : " + phoneNumber ); 
		}

		OTPResponseDTO otpResponseDTO=new OTPResponseDTO();
		otpResponseDTO.setPhoneNumber(phoneNumber);
		otpResponseDTO.setVerificationRef(verificationRef);
		otpResponseDTO.setStatusMessage("SUCCESS");
		return new ResponseEntity<>(otpResponseDTO, HttpStatus.OK);


	}



	//Admin will verify OTP to register Trainer
	@CrossOrigin
	@PostMapping("register/vitg/trainer/verifyOtp") 
	public ResponseEntity<TrainerDTO> validateTrainerOtp(@RequestBody PhoneVerificationDTO phoneVerificationDTO ) {

		boolean verified = otpService.verifyOtp(phoneVerificationDTO);

		if(verified) {

			User user = new User();
			user.setPhoneNumber(phoneVerificationDTO.getPhoneNumber());
			user.setLockTime(null);
			user.setAccountNonLocked(true);
			Role role= roleRepository.findByroleName("TRAINER");
			user.setRole(role);
			userService.saveUser(user);

			UserDTO userDTO=userService.saveUser(user);

			TrainerDTO trainerDTO= new TrainerDTO(); 
			trainerDTO.setId(userDTO.getId());
			trainerDTO.setPhoneNumber(userDTO.getPhoneNumber());
			trainerDTO.setRegistrationStatus(false);
			TrainerDTO trainerDTOResponse=trainerService.updateTrainer(trainerDTO);

			return new ResponseEntity<>(trainerDTOResponse, HttpStatus.CREATED);

		}else {
			throw new OTPException("Invalid OTP");
		}
	}

	@CrossOrigin
	@GetMapping("register/vitg/student/sendOtp") 
	public ResponseEntity<OTPResponseDTO> sendOtpForVitgStudentRegistration(@RequestParam String phoneNumber) {

		/*
		 * if (userRepository.existsByPhoneNumber(phoneNumber)) { throw new
		 * ResourceAlreadyExistsException("account with  : " + phoneNumber +
		 * " already exists"); }
		 */

		int otp = otpService.generateOTP();
		String verificationRef = otpService.generateVerificationRef();
		otpService.savePhoneVerification(new PhoneVerification(phoneNumber, verificationRef, String.valueOf(otp), 0));
		try {
			otpService.sendOTP(String.valueOf(otp),phoneNumber);
		}catch (AwsSnsClientException e)
		{ 
			throw new AwsSnsClientException("Not able to send SMS to : " + phoneNumber ); 
		}

		OTPResponseDTO otpResponseDTO = new OTPResponseDTO();
		otpResponseDTO.setPhoneNumber(phoneNumber);
		otpResponseDTO.setVerificationRef(verificationRef);
		otpResponseDTO.setStatusMessage("SUCCESS");
		return new ResponseEntity<>(otpResponseDTO, HttpStatus.OK);

	}

	@PostMapping("register/vitg/student/verifyOtp")
	public ResponseEntity<StudentDTO> validateStudentOtp(@RequestBody PhoneVerificationDTO phoneVerificationDTO ) {

		boolean verified = otpService.verifyOtp(phoneVerificationDTO);

		if(verified) {
			if (userRepository.existsByPhoneNumber(phoneVerificationDTO.getPhoneNumber())) {

				StudentDTO studentDTO = studentService.findByPhoneNumber(phoneVerificationDTO.getPhoneNumber()); 
				studentDTO.setStudentSubCourseIdList(studentSubCourseService.getSubCoursesByStudentId(studentDTO.getId()).stream().map(item -> item.getSubCourseId()).collect(Collectors.toList()));

				return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
			} else {
				User user= new User();
				user.setPhoneNumber(phoneVerificationDTO.getPhoneNumber());
				user.setLockTime(null);
				user.setAccountNonLocked(true);
				Role role= roleRepository.findByroleName("GUEST");
				user.setRole(role);
				userService.saveUser(user);

				UserDTO userDTO=userService.saveUser(user);

				StudentDTO studentDTO= new StudentDTO(); 
				studentDTO.setId(userDTO.getId());
				studentDTO.setPhoneNumber(userDTO.getPhoneNumber());
				studentDTO.setRegistrationStatus(false);
				StudentDTO studentDTOResponse=studentService.updateStudent(studentDTO);

				return new ResponseEntity<>(studentDTOResponse, HttpStatus.CREATED);
			}

		}else {
			throw new OTPException("Invalid OTP");
		}
	}


	@CrossOrigin
	@GetMapping("login/sendOtp")
	public ResponseEntity<OTPResponseDTO> sendOtp(@RequestParam String phoneNumber) throws LockedException{

		if (!userRepository.existsByPhoneNumber(phoneNumber)) {
			throw new RecordNotFoundException("Account with  : " + phoneNumber + " Not Exists : Please Register");
		}

		User user=userRepository.findByPhoneNumber(phoneNumber);

		if (user !=null && !user.isAccountNonLocked()) {
			System.out.println("test1***************");
			if (!userService.unlockWhenTimeExpired(user)) {
				System.out.println("test2******************");
				throw new LockedException("Your account has been Locked. Please try to login after Sometime.");

			}
			else {

				int otp = otpService.generateOTP();
				String verificationRef = otpService.generateVerificationRef();
				otpService.savePhoneVerification(new PhoneVerification(phoneNumber, verificationRef, String.valueOf(otp), 0));
				otpService.sendOTP(String.valueOf(otp),phoneNumber);

				OTPResponseDTO otpResponseDTO=new OTPResponseDTO();
				otpResponseDTO.setPhoneNumber(phoneNumber);
				otpResponseDTO.setVerificationRef(verificationRef);
				otpResponseDTO.setStatusMessage("SUCCESS");
				return new ResponseEntity<>(otpResponseDTO, HttpStatus.OK);
			}
		}
		else {

			int otp = otpService.generateOTP();
			String verificationRef = otpService.generateVerificationRef();

			otpService.savePhoneVerification(new PhoneVerification(phoneNumber, verificationRef, String.valueOf(otp), 0));
			otpService.sendOTP(String.valueOf(otp),phoneNumber);

			OTPResponseDTO otpResponseDTO=new OTPResponseDTO();
			otpResponseDTO.setPhoneNumber(phoneNumber);
			otpResponseDTO.setVerificationRef(verificationRef);
			otpResponseDTO.setStatusMessage("SUCCESS");
			return new ResponseEntity<>(otpResponseDTO, HttpStatus.OK);
		}
	}

	@CrossOrigin
	@PostMapping("login/verifyOtp")
	public ResponseEntity<UserDTO> verifyOtp(@RequestBody PhoneVerificationDTO phoneVerificationDTO ) {

		boolean verified =otpService.verifyOtp(phoneVerificationDTO);
		System.out.println("test1");
		if(verified)  {
			System.out.println("test2");
			if(userRepository.existsByPhoneNumber(phoneVerificationDTO.getPhoneNumber())){
				User user = userRepository.findByPhoneNumber(phoneVerificationDTO.getPhoneNumber());

				Role role = user.getRole();

				if(role.getRoleName().equals("TRAINER")) {
					user.setFailedAttempt(0);
					userRepository.save(user);
					Trainer trainer=trainerRepository.findByPhoneNumber(phoneVerificationDTO.getPhoneNumber());
					TrainerDTO trainerDTOResponse=modelMapper.map(trainer, TrainerDTO.class);

					UserDTO userDTO= new UserDTO();
					userDTO.setTrainerDTO(trainerDTOResponse);
					return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
				}

				if(role.getRoleName().equals("STUDENT")) {
					user.setFailedAttempt(0);
					userRepository.save(user);
					Student student=studentRepository.findByPhoneNumber(phoneVerificationDTO.getPhoneNumber());
					StudentDTO studentDTOResponse=modelMapper.map(student, StudentDTO.class);
					System.out.println(studentDTOResponse);
					UserDTO userDTO= new UserDTO();
					userDTO.setStudentDTO(studentDTOResponse);
					return new ResponseEntity<>(userDTO, HttpStatus.CREATED);

				}
				if(role.getRoleName().equals("ADMIN")||role.getRoleName().equals("MANAGER") ||
						role.getRoleName().equals("ACCOUNTANT")|| role.getRoleName().equals("ORGANIZER") ||
						role.getRoleName().equals("GUEST") || role.getRoleName().equals("SUPPORT_ENGINEER")) {
					System.out.println("test");
					user.setFailedAttempt(0);
					userRepository.save(user);
					Staff staff = staffRepository.findByPhoneNumber(phoneVerificationDTO.getPhoneNumber());
					StaffDTO staffDTOResponse = modelMapper.map(staff, StaffDTO.class);

					UserDTO userDTO= new UserDTO();
					userDTO.setVitgStaffDTO(staffDTOResponse);
					return new ResponseEntity<>(userDTO, HttpStatus.CREATED);

				}
			}	
			else {

				User user = new User();
				user.setPhoneNumber(phoneVerificationDTO.getPhoneNumber());
				User savedUser = userRepository.save(user);
				UserDTO userDTO= new UserDTO();
				userDTO.setId(savedUser.getId());
				userDTO.setPhoneNumber(savedUser.getPhoneNumber());

				return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
				/*
				 * throw new RecordNotFoundException("account with  : " +
				 * phoneVerificationDTO.getPhoneNumber() + " Not Exists : Please register");
				 */
			}																																																							

		}else {
			System.out.println("test2");
			User user=userRepository.findByPhoneNumber(phoneVerificationDTO.getPhoneNumber());

			if (user != null && user.isAccountNonLocked()) {
				if (user.getFailedAttempt() < userServiceImpl.MAX_FAILED_ATTEMPTS - 1) {
					int failledAttemptsCount=  userService.increaseFailedAttempts(user);
					throw new OTPException("Invalid OTP  : Failed attempts : "+ failledAttemptsCount);
				} else {
					userService.lock(user);
					throw new LockedException("Your account has been locked due to 3 failed attempts."
							+ " It will be unlocked after 2 minutes.");
				}
			}
		}
		return null;
	}
}
