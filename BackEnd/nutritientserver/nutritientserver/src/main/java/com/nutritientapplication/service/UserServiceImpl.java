package com.nutritientapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutritientapplication.entity.User;
import com.nutritientapplication.exception.UserAlreadyExistsException;
import com.nutritientapplication.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	//creating or adding user
	@Override
	public User createUser(User u) throws UserAlreadyExistsException {
		User local = this.userRepository.findByUsername(u.getUsername());
		if(local!=null) {
			System.out.println("User is already there !!");
			throw new UserAlreadyExistsException("User already exists");
		}else {
			local = this.userRepository.save(u);
		}
		
		return local;
	}
	
    //getting user by username
	@Override
	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}

}
