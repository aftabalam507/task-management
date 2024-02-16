package com.user.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.config.JwtProvider;
import com.user.model.User;
import com.user.repo.UserRepository;
import com.user.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUserProfile(String jwt) {
	
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		
		return this.userRepository.findByEmail(email);
	}

	@Override
	public List<User> getAllUsers() {
		
		return this.userRepository.findAll();
	}

}
