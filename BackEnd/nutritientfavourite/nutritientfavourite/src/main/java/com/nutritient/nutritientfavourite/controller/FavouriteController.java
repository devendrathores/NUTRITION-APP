package com.nutritient.nutritientfavourite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutritient.nutritientfavourite.dto.AddFavouriteRequest;
import com.nutritient.nutritientfavourite.dto.FavouriteFoodDetails;
import com.nutritient.nutritientfavourite.dto.RemoveFavouriteRequest;
import com.nutritient.nutritientfavourite.exception.FoodAlreadyExistsException;
import com.nutritient.nutritientfavourite.exception.NoFoodFoundException;
import com.nutritient.nutritientfavourite.service.IFavouriteTrackService;




@RestController
@RequestMapping("/food")
public class FavouriteController {
	  @Autowired
	    private IFavouriteTrackService service;

	    @GetMapping("/{username}")
	    public List<FavouriteFoodDetails> findAll(@PathVariable("username") String username) throws NoFoodFoundException {
	        List<FavouriteFoodDetails> response = service.listFavouriteTracksByUserName(username);
	        System.out.println(response); 
	        return response;
	    }

	    @PostMapping("/add")
	    public FavouriteFoodDetails add(@RequestBody AddFavouriteRequest requestData) throws FoodAlreadyExistsException {
	        FavouriteFoodDetails response = service.addToFavourite(requestData);
	        return response;
	    }

	
	    @DeleteMapping("/delete/{username}/{fdcId}")
	    public FavouriteFoodDetails delete(@PathVariable("username") String username, @PathVariable()String fdcId)throws NoFoodFoundException {
	    	FavouriteFoodDetails response =service.deleteFavourite(username, fdcId);
	    	return response;
	    }

}
