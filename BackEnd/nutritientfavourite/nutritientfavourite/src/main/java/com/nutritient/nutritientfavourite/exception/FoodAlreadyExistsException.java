package com.nutritient.nutritientfavourite.exception;

public class FoodAlreadyExistsException extends Exception{
    public FoodAlreadyExistsException(String message) {
        super(message);
    }
}