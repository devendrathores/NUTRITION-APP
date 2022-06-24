package com.nutritient.nutritientfavourite.service;

import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import com.nutritient.nutritientfavourite.dto.AddFavouriteRequest;
import com.nutritient.nutritientfavourite.dto.FavouriteFoodDetails;
import com.nutritient.nutritientfavourite.entity.FavoritedItem;
import com.nutritient.nutritientfavourite.entity.Nutrient;
import com.nutritient.nutritientfavourite.exception.FoodAlreadyExistsException;
import com.nutritient.nutritientfavourite.exception.NoFoodFoundException;
import com.nutritient.nutritientfavourite.repository.IFavouriteTrackRepository;
import com.nutritient.nutritientfavourite.util.FavouriteTrackUtil;

@ExtendWith(MockitoExtension.class)
class FavouriteFoodServiceImplTest {
    @Mock
    IFavouriteTrackRepository repository;

    @Mock
    FavouriteTrackUtil util;

    @InjectMocks
    @Spy
    FavouriteTrackServiceImpl service;

    /*
    Scenario: When list is successfully fetched
    input: userId: arghya@
    expectation : List successfully fetched
    verifying IFavouritFoodRepository#findByUserId(userId) is called once

     */

    @Test
    public void testListFavoriteItemsByUserId_1()throws NoFoodFoundException {
        String username="devendrathore";
        List<FavoritedItem>foods=mock(List.class);
        List<FavouriteFoodDetails>details=mock(List.class);
        when(repository.findByUsername(username)).thenReturn(foods);
        when(util.toFavouriteTrackDetails(foods)).thenReturn(details);
        List<FavouriteFoodDetails> result=service.listFavouriteTracksByUserName(username);
        assertSame(details, result);
        verify(repository).findByUsername(username);
        verify(util).toFavouriteTrackDetails(foods);
    }



     /*
    Scenario: When list is empty,  FoodNotFoundException is thrown
    input: userId: arghya@
    expectation : FoodNotFoundException is thrown
   verifying IFavouritFoodRepository#findByUserId(userId) is called once

     */
   /*
    @Test
     public void testListFavoriteItemsByUserId_2()throws FoodNotFoundException {
         String userId = "arghya@";
         List<FavoritedItem> foods = mock(List.class);
         when(repository.findByUserName(userId)).thenReturn(foods);
         when(foods.isEmpty()).thenReturn(true);
         Executable executable=()->{
             service.listFavoriteItemsByUserName(userId);
         };
         assertThrows(FoodNotFoundException.class, executable);
         verify(repository).findByUserName(userId);
     }

    */



     /*
    Scenario: FavoritedItem is added successfully
    Input: AddFavouriteFoodRequest data
    expectation : FavoritedItem is added, FavoritedItemDetails is returned
    verify repository#save() is called once
     */

    @Test
    public void testAddFavouriteFood_1()throws Exception{
        AddFavouriteRequest request=new AddFavouriteRequest();
        List<Nutrient> nutrients=mock(List.class);
        request.setFdcId("1900168");
        request.setDescription("ALMOND MILK, ORIGINAL");
        request.setBrandOwner("H E Butt Grocery Company");
        request.setUsername("arghya@");
        request.setFoodNutrients(nutrients);
        String id="1252";
        doReturn(id).when(service).generateId();
        Optional<FavoritedItem>optional=Optional.empty();
        when(repository.findByUsernameAndFdcId(request.getUsername(), request.getFdcId())).thenReturn(optional);
        FavoritedItem favoritedItem=mock(FavoritedItem.class);
        FavoritedItem saveFavourite=mock(FavoritedItem.class);
        when(util.toFavouriteTrack(request)).thenReturn(favoritedItem);
        when(repository.save(favoritedItem)).thenReturn(saveFavourite);

        FavouriteFoodDetails favoritedFoodDetails=mock(FavouriteFoodDetails.class);
        when(util.toFavouriteTrackDetails(saveFavourite)).thenReturn(favoritedFoodDetails);
        FavouriteFoodDetails result=service.addToFavourite(request);
        assertSame(favoritedFoodDetails, result);
        verify(repository).save(favoritedItem);

    }

     /*
    Scenario: when FavoritedItem  already exist
    Input: AddFavouriteFoodRequest data
    expectation : FavoritedItemExistException is thrown
    verify repository#save(favoritedItem) is never called

     */
    @Test
    public void testAddFavouriteFood_2()throws Exception {
        AddFavouriteRequest request = new AddFavouriteRequest();
        List<Nutrient> foodNutrients = mock(List.class);
        request.setFdcId("1900168");
        request.setDescription("ALMOND MILK, ORIGINAL");
        request.setBrandOwner("H E Butt Grocery Company");
        request.setUsername("arghya@");
        request.setFoodNutrients(foodNutrients);
        FavoritedItem favoritedItem=mock(FavoritedItem.class);
        Optional<FavoritedItem>optional=Optional.of(favoritedItem);
        when(repository.findByUsernameAndFdcId(request.getUsername(), request.getFdcId())).thenReturn(optional);
        Executable executable=()->{
            service.addToFavourite(request);
        };
        assertThrows(FoodAlreadyExistsException.class, executable);
        verify(repository,never()).save(favoritedItem);

    }


    /*
    Scenario: when FavoritedItem  is exist
    Input: RemoveFavorited data
    expectation : FoodNotFoundException is thrown
    verify repository#delete(favoritedItem) is called

     */

   /* @Test
    public void testRemoveFavorite_1() throws Exception{
        RemoveFavorited requestData=new RemoveFavorited();
        requestData.setFdcId(1255222);
        requestData.setUsername("arghya@");
        FavoritedItem favoritedItem=mock(FavoritedItem.class);
        Optional<FavoritedItem>optional=Optional.of(favoritedItem);
        when(repository.findByUsernameAndFdcId(requestData.getUsername(), requestData.getFdcId())).thenReturn(optional);
        FavoritedItemDetails favoritedItemDetails=mock(FavoritedItemDetails.class);
        when(util.toFavouritFoodDetails(favoritedItem)).thenReturn(favoritedItemDetails);
        FavoritedItemDetails result=service.removeFavorite(requestData);
        assertSame(favoritedItemDetails, result);
        verify(repository).delete(favoritedItem);
    }*/




    /*
    Scenario: when FavoritedItem  is not exist
    Input: RemoveFavorited data
    expectation : FoodNotFoundException is thrown
    verify repository#delete(favoritedItem) is never called

     */

  /*  @Test
    public void testRemoveFavorite_2() throws Exception {
        RemoveFavorited requestData = new RemoveFavorited();
        requestData.setFdcId(1255222);
        requestData.setUsername("arghya@");
        FavoritedItem favoritedItem = mock(FavoritedItem.class);
        Optional<FavoritedItem>optional=Optional.empty();
        when(repository.findByUsernameAndFdcId(requestData.getUsername(), requestData.getFdcId())).thenReturn(optional);
        Executable executable=()->{
            service.removeFavorite(requestData);
        };
        assertThrows(FoodNotFoundException.class, executable);
        verify(repository, never()).delete(favoritedItem);


    }*/











}
