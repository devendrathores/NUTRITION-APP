package com.nutritientapplication.controller;


//Jwt
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nutritientapplication.config.JwtUtils;
import com.nutritientapplication.entity.JwtRequest;
import com.nutritientapplication.entity.JwtResponse;
import com.nutritientapplication.entity.User;
import com.nutritientapplication.service.UserSecurityServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserSecurityServiceImpl userSecurityServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtil;
	
	//generate token
	@PostMapping("/user/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		try {
			
	 authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("User not found");
		}
		UserDetails userDetails = this.userSecurityServiceImpl.loadUserByUsername(jwtRequest.getUsername());
	    String token = this.jwtUtil.generateToken(userDetails);
	    return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	private void authenticate(String username,String password) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
			
		}catch(DisabledException e) {
			throw new Exception("USER DISABLED");
		}catch(BadCredentialsException e) {
			throw new Exception("Invalid Credentials");
		}
	
		
	}
	
	//return the details of current user who is LogedIn
	@GetMapping("/user/current-user")
	public User getCurrentUser(Principal principal) {
		return ((User)this.userSecurityServiceImpl.loadUserByUsername(principal.getName()));
	}
	
}
