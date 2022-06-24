package com.nutritient.nutritientfavourite.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.nutritient.nutritientfavourite.dto.AddFavouriteRequest;
import com.nutritient.nutritientfavourite.dto.FavouriteFoodDetails;
import com.nutritient.nutritientfavourite.dto.RemoveFavouriteRequest;
import com.nutritient.nutritientfavourite.entity.Nutrient;
import com.nutritient.nutritientfavourite.exception.FoodAlreadyExistsException;
import com.nutritient.nutritientfavourite.exception.NoFoodFoundException;
import com.nutritient.nutritientfavourite.service.IFavouriteTrackService;




@WebMvcTest(FavouriteController.class)
public class FavouriteControllerTest {
	
	 @MockBean
	 IFavouriteTrackService service;

	 @Autowired
	 MockMvc mvc;
	 
	 private FavouriteFoodDetails response;
	 
	 @BeforeEach
	    public void setUp() {
	        response = new FavouriteFoodDetails();
	       response.setFdcId("1234");
	       response.setDescription("ALMOND MILK, ORIGINAL");
	       response.setBrandOwner("H E Butt Grocery Company");

	        Nutrient foodNutrient1=new Nutrient();
	        foodNutrient1.setNumber("125");
	        foodNutrient1.setName("Protein");
	        foodNutrient1.setAmount(1);
	        foodNutrient1.setUnitName("G");
	        foodNutrient1.setDerivationCode("LCCS");
	        foodNutrient1.setDerivationDescription("Calculated from value per serving size measure");
	        List<Nutrient>nutrients=new ArrayList<>();
	        nutrients.add(foodNutrient1);
	        response.setFoodNutrients(nutrients);

	    }
	 
	 @AfterEach
	    public void tearDown() {
	        response = null;
	    }

	    /**
	     * scenario: FavoritedItem is  found
	     * input : username="devendrathore"
	     * expectation: FavoritedItemDetails returned as response, status 200/ok
	     */
	 
	 @Test
	    public void testGetAllFavouriteFoodByUserName()throws Exception{
	        String userName="devendrathore";
	        response.setUsername(userName);
	        List<FavouriteFoodDetails> details=new ArrayList<>();
	        details.add(response);
	        when(service.listFavouriteTracksByUserName(userName)).thenReturn(details);
	        ObjectMapper objectMapper=new ObjectMapper();
	        String json=objectMapper.writeValueAsString(details);
	        String url="/food/"+userName;
	        mvc.perform(get(url))
	                .andExpect(status().isOk())
	                .andExpect(content().json(json));

	    }
	 
	 /**
	     * scenario: FavoritedItem added successfully
	     * input : AddFavouriteRequest data
	     * expectation: FoodAlreadyExistsException is thrown. status 200/ok
	     */
	 
	 
	 @Test
	    public void testAddFavouriteFood_1()throws Exception{
	        String userName="devendrathore";
	        AddFavouriteRequest requestdata=new AddFavouriteRequest();
	        requestdata.setUsername(userName);
	        requestdata.setFdcId("1909790");
	        requestdata.setDescription("ORIGINAL ALMOND MILK, ORIGINAL");
	        requestdata.setBrandOwner("Aldi Inc.");

	        Nutrient foodNutrient1=new Nutrient();
	        foodNutrient1.setNumber("203");
	        foodNutrient1.setName("Protein");
	        foodNutrient1.setAmount(0);
	        foodNutrient1.setUnitName("G");
	        foodNutrient1.setDerivationCode("LCCS");
	        foodNutrient1.setDerivationDescription("Calculated from value per serving size measure");
	        List<Nutrient>nutrients=new ArrayList<>();
	        nutrients.add(foodNutrient1);
	        requestdata.setFoodNutrients(nutrients);
	        response.setUsername(userName);
	        when(service.addToFavourite(requestdata)).thenReturn(response);
	        ObjectMapper objectMapper=new ObjectMapper();
	        String jsonRequest=objectMapper.writeValueAsString(requestdata);
	        String jsonResponse=objectMapper.writeValueAsString(response);
	        String url="/food/add";
	        mvc.perform(post(url)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(jsonRequest))
	                .andExpect(status().isOk())
	                .andExpect(content().json(jsonResponse));
	        verify(service).addToFavourite(requestdata);
	    }
	 
	 
	 /**
	     * scenario: FavoritedItem already exist
	     * input : AddFavouriteRequest data
	     * expectation: FoodAlreadyExistsException is thrown. status 400/BAD_REQUEST
	     */
	 
	 
	 @Test
	    public void testAddFavouriteFood_2()throws Exception{
	        String userName="devendrathore";
	        AddFavouriteRequest requestdata=new AddFavouriteRequest();
	        requestdata.setUsername(userName);
	        requestdata.setFdcId("1909790");
	        requestdata.setDescription("ORIGINAL ALMOND MILK, ORIGINAL");
	        requestdata.setBrandOwner("Aldi Inc.");

	        Nutrient foodNutrient1=new Nutrient();
	        foodNutrient1.setNumber("203");
	        foodNutrient1.setName("Protein");
	        foodNutrient1.setAmount(0);
	        foodNutrient1.setUnitName("G");
	        foodNutrient1.setDerivationCode("LCCS");
	        foodNutrient1.setDerivationDescription("Calculated from value per serving size measure");
	        List<Nutrient>nutrients=new ArrayList<>();
	        nutrients.add(foodNutrient1);
	        requestdata.setFoodNutrients(nutrients);

	        String msg="Track is already in the favourite list";
	        FoodAlreadyExistsException exception=new FoodAlreadyExistsException(msg);
	        when(service.addToFavourite(requestdata)).thenThrow(exception);
	        ObjectMapper objectMapper=new ObjectMapper();
	        String jsonRequest=objectMapper.writeValueAsString(requestdata);
	        String url="/food/add";

	        mvc.perform(post(url)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(jsonRequest))
	                .andExpect(status().isBadRequest())
	                .andExpect(content().string(msg));


	    }
	 
	 
	 /**
	     * scenario: FavoritedItem is removed successfully
	     * input : deleteFavorited data
	     * expectation:  FavoritedItem removed successfully. status 200/OK
	     */

	 @Test
	    public void testRemoveFavourit_1()throws Exception{
	        String userName="devendrathore";
	        String fdcId="1234";
	        response.setUsername(userName);
	        when(service.deleteFavourite(userName,fdcId)).thenReturn(response);
	        ObjectMapper objectMapper=new ObjectMapper();
	        String jsonResponse=objectMapper.writeValueAsString(response);
	        String url="/food/delete/"+userName+"/"+fdcId;
	        mvc.perform(delete(url))
	                .andExpect(status().isOk())
	                .andExpect(content().json(jsonResponse));

	        verify(service).deleteFavourite(userName,fdcId);

	    }
	 
	 /**
	     * scenario: FavoritedItem is not found
	     * input : deleteFavourite
	     * expectation:  NoFoodFoundException. status 404/NOT_FOUND
	     */

	    @Test
	    public void testRemoveFavourit_2()throws Exception {
	    	 String userName="devendrathore";
		      String fdcId="1234";
	       
	       
	        response.setUsername(userName);

	        String msg="No food found";
	        NoFoodFoundException exception=new NoFoodFoundException(msg);
	        when(service.deleteFavourite(userName,fdcId)).thenThrow(exception);
	        String url="/food/delete/"+userName+"/"+fdcId;
	        mvc.perform(delete(url))
	                .andExpect(status().isNotFound())
	                .andExpect(content().string(msg));
	        verify(service).deleteFavourite(userName,fdcId);
	    }




}
