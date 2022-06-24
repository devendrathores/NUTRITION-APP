package com.nutritient.nutritientfavourite.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nutritient.nutritientfavourite.exception.FoodAlreadyExistsException;
import com.nutritient.nutritientfavourite.exception.NoFoodFoundException;


@RestControllerAdvice/*Will catch all exceptions*/
public class CentralizedExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FoodAlreadyExistsException.class)
    public String trackAlreadyExists(FoodAlreadyExistsException e) {
        String msg = e.getMessage();
        return msg;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoFoodFoundException.class)
    public String noTrackFound(NoFoodFoundException e) {
        String msg = e.getMessage();
        return msg;
    }
}