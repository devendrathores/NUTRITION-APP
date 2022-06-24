package com.nutritientapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutritientapplication.entity.User;
import com.nutritientapplication.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	//creating or adding user
	@Override
	public User createUser(User u) throws Exception {
		User local = this.userRepository.findByUserName(u.getUserName());
		if(local!=null) {
			System.out.println("User is already there !!");
			throw new Exception("User already exists");
		}else {
			local = this.userRepository.save(u);
		}
		
		return local;
	}
	
    //getting user by username
	@Override
	public User getUser(String username) {
		return this.userRepository.findByUserName(username);
	}

}
