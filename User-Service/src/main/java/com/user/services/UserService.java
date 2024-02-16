package com.user.services;

import java.util.List;

import com.user.model.User;

public interface UserService {
	
	public User getUserProfile(String jwt);
	
	public List<User> getAllUsers();
}
