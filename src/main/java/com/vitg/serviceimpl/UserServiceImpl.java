package com.vitg.serviceimpl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitg.dto.UserDTO;
import com.vitg.entity.User;
import com.vitg.repository.UserRepository;
import com.vitg.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	public static final int MAX_FAILED_ATTEMPTS = 3;

	private static final long LOCK_TIME_DURATION =2 * 60 * 1000;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO saveUser(User userInfo) {
		User user = userRepository.save(userInfo);
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setPhoneNumber(user.getPhoneNumber());
		return userDTO;
	}



	@Transactional
	public int increaseFailedAttempts(User user) {
		int newFailAttempts = user.getFailedAttempt() + 1;
		userRepository.updateFailedAttempts(newFailAttempts, user.getPhoneNumber());
		return newFailAttempts;
	}
	@Transactional
	public void resetFailedAttempts(String phoneNumber) {
		userRepository.updateFailedAttempts(0, phoneNumber);
	}

	public void lock(User user) {
		user.setAccountNonLocked(false);
		user.setLockTime(new Date());

		userRepository.save(user);
	}

	public boolean unlockWhenTimeExpired(User user) {
		long lockTimeInMillis = user.getLockTime().getTime();
		long currentTimeInMillis = System.currentTimeMillis();

		if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
			user.setAccountNonLocked(true);
			user.setLockTime(null);
			user.setFailedAttempt(0);

			userRepository.save(user);

			return true;
		}

		return false;
	}
}
