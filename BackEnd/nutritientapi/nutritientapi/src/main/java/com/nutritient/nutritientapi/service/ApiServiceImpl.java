package com.nutritient.nutritientapi.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nutritient.nutritientapi.exception.NoFoodFoundException;

@Service
public class ApiServiceImpl implements ApiService {

	String API_KEY="rYXdoXhDOv5tJMbkUGjOuNb4QRqRZCciNUOFwjmn";
	
	@Override
	public List<Object> getFoodApi() {
		String url="https://api.nal.usda.gov/fdc/v1/foods/list?api_key="+this.API_KEY;
	    
		RestTemplate restTemplate = new RestTemplate();
	    Object[] foods = restTemplate.getForObject(url, Object[].class);
	    return Arrays.asList(foods);
	}


	@Override
	public List<Object> getFoodSearchApi(String query, String pageNo)throws NoFoodFoundException {
		String url="https://api.nal.usda.gov/fdc/v1/foods/list?dataType=Branded&query="+query+"&pageSize=25&pageNumber="+pageNo+"&api_key="+this.API_KEY;
		
		RestTemplate restTemplate = new RestTemplate();
		Object[] foods = restTemplate.getForObject(url, Object[].class);
		return Arrays.asList(foods);
	}

}
