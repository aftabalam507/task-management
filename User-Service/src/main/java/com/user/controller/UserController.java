package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.User;
import com.user.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt){
		
		User user = this.userService.getUserProfile(jwt);
		
		return new ResponseEntity<>(user,HttpStatus.OK);			
	}
	
	@GetMapping()
	public ResponseEntity<List<User>> getUsers(@RequestHeader("Authorization") String jwt){
		
		 List<User> list = this.userService.getAllUsers();
		
		return new ResponseEntity<>(list,HttpStatus.OK);			
	}
	
	
}
