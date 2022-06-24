package com.nutritientapplication.service;
//Jwt

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nutritientapplication.entity.User;
import com.nutritientapplication.repo.UserRepository;

@Service
public class UserSecurityServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
 
	/*It will use to load user from database for validating username and password*/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);
		if(user==null) {
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("INVALID CREDENTIALS. No user found !!");
		}
		return user;
	}

}
