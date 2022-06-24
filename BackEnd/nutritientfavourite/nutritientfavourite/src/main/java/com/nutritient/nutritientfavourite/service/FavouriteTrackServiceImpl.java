package com.nutritient.nutritientfavourite.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutritient.nutritientfavourite.dto.AddFavouriteRequest;
import com.nutritient.nutritientfavourite.dto.FavouriteFoodDetails;
import com.nutritient.nutritientfavourite.dto.RemoveFavouriteRequest;
import com.nutritient.nutritientfavourite.entity.FavoritedItem;
import com.nutritient.nutritientfavourite.exception.FoodAlreadyExistsException;
import com.nutritient.nutritientfavourite.exception.NoFoodFoundException;
import com.nutritient.nutritientfavourite.repository.IFavouriteTrackRepository;
import com.nutritient.nutritientfavourite.util.FavouriteTrackUtil;


@Service
public class FavouriteTrackServiceImpl implements IFavouriteTrackService {
    @Autowired
    private IFavouriteTrackRepository repository;

    @Autowired
    private FavouriteTrackUtil util;

    private Random random = new Random();


    public String generateId() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int randomNum = random.nextInt(99);
            builder.append(randomNum);
        }
        String id = builder.toString();
        return id;
    }

    @Override
    public FavouriteFoodDetails addToFavourite(AddFavouriteRequest requestData) throws FoodAlreadyExistsException {
        Optional<FavoritedItem> optional = repository.findByUsernameAndFdcId(requestData.getUsername(),requestData.getFdcId());
        if (optional.isPresent()) {
            throw new FoodAlreadyExistsException("Food is already in the favourite list");
        }
        FavoritedItem item = new FavoritedItem();
        item = util.toFavouriteTrack(requestData);
        String id=generateId();
        item.setId(id);
        item=repository.save(item);
        FavouriteFoodDetails details = util.toFavouriteTrackDetails(item);
        return details;
    }

    /* Instead of removeFavourite(RemoveFavouriteRequest requestData). We are using  deleteFavourite(String username,String fdcId) for removing favourite food items.*/
    @Override
    public FavouriteFoodDetails removeFavourite(RemoveFavouriteRequest requestData) throws NoFoodFoundException {
        Optional<FavoritedItem> optional = repository.findByUsernameAndFdcId(requestData.getUsername(),requestData.getFdcId());
        if (!optional.isPresent()) {
            throw new NoFoodFoundException("No Food found");
        }
        FavoritedItem item = optional.get();
        repository.delete(item);
        FavouriteFoodDetails details = util.toFavouriteTrackDetails(item);
        return details;
    }

    @Override
    public List<FavouriteFoodDetails> listFavouriteTracksByUserName(String username) throws NoFoodFoundException {
        List<FavoritedItem> items = repository.findByUsername(username);
        if (items.isEmpty()) {
            throw new NoFoodFoundException("No Food found");
        }
        List<FavouriteFoodDetails> desired = util.toFavouriteTrackDetails(items);
        return desired;
    }

	@Override
	public FavouriteFoodDetails deleteFavourite(String username, String fdcId) throws NoFoodFoundException {
		Optional<FavoritedItem> optional = repository.findByUsernameAndFdcId(username, fdcId);
		if(!optional.isPresent()) {
			throw new NoFoodFoundException("No Food found");
		}
		FavoritedItem item = optional.get();
		repository.delete(item);
		FavouriteFoodDetails details = util.toFavouriteTrackDetails(item);
		return details;
	}

	
}
