package com.nutritientapplication.service;

import com.nutritientapplication.entity.User;
import com.nutritientapplication.exception.UserAlreadyExistsException;

public interface UserService {
	
	//creating user
	public User createUser(User u) throws UserAlreadyExistsException;

	//get user by username
	public User getUser(String username);
}
