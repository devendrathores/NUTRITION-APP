package com.nutritientapplication.config;
//Jwt
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nutritientapplication.service.UserSecurityServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

/*This class will run first for filtering header from request.*/
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserSecurityServiceImpl userSecurityServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println(requestTokenHeader);
		String username=null;
		String jwtToken=null;
		
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			
			jwtToken=requestTokenHeader.substring(7);
			try {
			username=this.jwtUtil.extractUsername(jwtToken);
			}catch(ExpiredJwtException e) {
				/*to print error on console*/
				e.printStackTrace();
				System.out.println("JWT Token has expired");
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
		}
		else {
			System.out.println("Invalid token, not starts with bearer");
		}
		
		//security
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) 
		{
		  final UserDetails userDetails = this.userSecurityServiceImpl.loadUserByUsername(username);
		    if(this.jwtUtil.validateToken(jwtToken, userDetails)) {
		    	
		    	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());  
		    	usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
		    	SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		    	
		    }
		
		}else {
			System.out.println("Token is not valid");
		}
		
		/*Finally it will forward the filter data*/
		filterChain.doFilter(request, response);
	}
	
	

}
