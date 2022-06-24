package com.nutritientapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutritientapplication.entity.User;
import com.nutritientapplication.exception.UserAlreadyExistsException;
import com.nutritientapplication.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")/*This annotation will allow angular to hit this port url easily. As if we don't use this annotation then the angular request with its port will be blocked. So now it will allow all cross origin connection with all ports or hosts.*/
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	//creating user(SignUp)
	@PostMapping("/add")
	public User createUser(@RequestBody User u) throws UserAlreadyExistsException  {
		
		return this.userservice.createUser(u);
	}
	//
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		
		return userservice.getUser(username); 
	}

}
