package com.nutritient.nutritientfavourite.dto;

import java.util.List;

import com.nutritient.nutritientfavourite.entity.Nutrient;



public class FavouriteFoodDetails {

    private String username;
    private String fdcId;
    private String description;
    private String brandOwner;
    private List<Nutrient> foodNutrients;

   

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFdcId() {
        return fdcId;
    }

    public void setFdcId(String fdcId) {
        this.fdcId = fdcId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrandOwner() {
        return brandOwner;
    }

    public void setBrandOwner(String brandOwner) {
        this.brandOwner = brandOwner;
    }

    public List<Nutrient> getFoodNutrients() {
        return foodNutrients;
    }

    public void setFoodNutrients(List<Nutrient> foodNutrients) {
        this.foodNutrients = foodNutrients;
    }
}
