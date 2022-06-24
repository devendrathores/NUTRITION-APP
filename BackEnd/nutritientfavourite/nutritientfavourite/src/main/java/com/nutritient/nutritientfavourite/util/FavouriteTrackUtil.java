package com.nutritient.nutritientfavourite.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nutritient.nutritientfavourite.dto.AddFavouriteRequest;
import com.nutritient.nutritientfavourite.dto.FavouriteFoodDetails;
import com.nutritient.nutritientfavourite.entity.FavoritedItem;



@Component
public class FavouriteTrackUtil {

    public FavouriteFoodDetails toFavouriteTrackDetails(FavoritedItem item){
        FavouriteFoodDetails desired=new FavouriteFoodDetails();
        desired.setUsername(item.getUsername());
        desired.setFdcId(item.getFdcId());
        desired.setDescription(item.getDescription());
        desired.setBrandOwner(item.getBrandOwner());
        desired.setFoodNutrients(item.getFoodNutrients());
        return desired;

    }
    public FavoritedItem toFavouriteTrack(AddFavouriteRequest addFavouriteRequest){
        FavoritedItem desired=new FavoritedItem();
        desired.setUsername(addFavouriteRequest.getUsername());
        desired.setFdcId(addFavouriteRequest.getFdcId());
        desired.setDescription(addFavouriteRequest.getDescription());
        desired.setBrandOwner(addFavouriteRequest.getBrandOwner());
        desired.setFoodNutrients(addFavouriteRequest.getFoodNutrients());
        return desired;

    }


    public List<FavouriteFoodDetails> toFavouriteTrackDetails(List<FavoritedItem> tracks){
        List<FavouriteFoodDetails> desired=new ArrayList<>();
        for(FavoritedItem track:tracks){
            FavouriteFoodDetails details=toFavouriteTrackDetails(track);
            desired.add(details);

        }
        return desired;
    }
}

