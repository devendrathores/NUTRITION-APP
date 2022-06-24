package com.nutritient.nutritientapi.service;

import java.util.List;

import com.nutritient.nutritientapi.exception.NoFoodFoundException;

public interface ApiService {

	public List<Object> getFoodApi();
	
	public List<Object> getFoodSearchApi(String query, String pageNo) throws NoFoodFoundException;
}
