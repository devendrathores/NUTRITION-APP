package com.nutritient.nutritientfavourite.dto;

import java.util.List;
import java.util.Objects;


import com.nutritient.nutritientfavourite.entity.Nutrient;


public class AddFavouriteRequest {

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

	@Override
	public int hashCode() {
		return Objects.hash(fdcId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddFavouriteRequest other = (AddFavouriteRequest) obj;
		return Objects.equals(fdcId, other.fdcId) && Objects.equals(username, other.username);
	}

	
}

