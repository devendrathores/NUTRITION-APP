package com.nutritient.nutritientfavourite.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.nutritient.nutritientfavourite.dto.AddFavouriteRequest;
import com.nutritient.nutritientfavourite.dto.FavouriteFoodDetails;
import com.nutritient.nutritientfavourite.dto.RemoveFavouriteRequest;
import com.nutritient.nutritientfavourite.exception.FoodAlreadyExistsException;
import com.nutritient.nutritientfavourite.exception.NoFoodFoundException;



@Validated
public interface IFavouriteTrackService {

    FavouriteFoodDetails addToFavourite(AddFavouriteRequest requestData) throws FoodAlreadyExistsException;
  
    List<FavouriteFoodDetails> listFavouriteTracksByUserName(String username) throws NoFoodFoundException;

    FavouriteFoodDetails deleteFavourite(String username,String fdcId) throws NoFoodFoundException;

    /* Instead of removeFavourite(RemoveFavouriteRequest requestData). We are using  deleteFavourite(String username,String fdcId) for removing favourite food items.*/
    FavouriteFoodDetails removeFavourite(RemoveFavouriteRequest requestData) throws NoFoodFoundException;


}
