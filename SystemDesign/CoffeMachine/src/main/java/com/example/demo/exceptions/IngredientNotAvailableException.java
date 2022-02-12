package com.example.demo.exceptions;

public class IngredientNotAvailableException extends RuntimeException{

    public IngredientNotAvailableException(String message) {
        super(message);
    }

}
