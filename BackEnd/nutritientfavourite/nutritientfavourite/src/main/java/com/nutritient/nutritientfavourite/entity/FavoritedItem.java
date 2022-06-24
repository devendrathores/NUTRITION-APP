package com.nutritient.nutritientfavourite.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document("favourite")
public class FavoritedItem {
    @Id
    private String id;
    private String username;
    private String fdcId;
    private String description;
    private String brandOwner;
    private List<Nutrient> foodNutrients;

    public List<Nutrient> getFoodNutrients() {
        return foodNutrients;
    }

    public void setFoodNutrients(List<Nutrient> foodNutrients) {
        this.foodNutrients = foodNutrients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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


}
