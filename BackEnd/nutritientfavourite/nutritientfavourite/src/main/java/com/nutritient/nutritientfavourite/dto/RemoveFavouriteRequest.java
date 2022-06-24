package com.nutritient.nutritientfavourite.dto;
/* I created another method for removing favourite food deleteFavourite(). So this class is not needed*/
public class RemoveFavouriteRequest {
    private String username;
    private String fdcId;

    

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
}