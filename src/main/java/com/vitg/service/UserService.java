package com.vitg.service;

import com.vitg.dto.UserDTO;
import com.vitg.entity.User;

public interface UserService {

    public UserDTO saveUser(User user);
	public int increaseFailedAttempts(User user);
    public void resetFailedAttempts(String phoneNumber);
    public void lock(User user); 
    public boolean unlockWhenTimeExpired(User user);
}
