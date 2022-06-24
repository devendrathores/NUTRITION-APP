package com.nutritient.nutritientfavourite.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nutritient.nutritientfavourite.entity.FavoritedItem;



@Repository
public interface IFavouriteTrackRepository extends MongoRepository<FavoritedItem, String> {

    List<FavoritedItem> findByUsername(String username);


    Optional<FavoritedItem> findByUsernameAndFdcId(String username, String fdcId);

}

