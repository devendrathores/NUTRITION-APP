package com.nutritient.nutritientapi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nutritient.nutritientapi.exception.NoFoodFoundException;
import com.nutritient.nutritientapi.service.ApiServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ApiController {

	@Autowired
	private ApiServiceImpl apiServiceImpl;
	
	//get all food
	@GetMapping("/food")
	public List<Object> getFoodApi(){
		return this.apiServiceImpl.getFoodApi();
	}
	
	@GetMapping("/{query}/{pageNo}")
	public List<Object> getFoodBySearch(@PathVariable("query") String query, @PathVariable("pageNo") String pageno)throws NoFoodFoundException{
		return this.apiServiceImpl.getFoodSearchApi(query, pageno);
	}
	
	
}
