package com.example.demo.exceptions;

public class IngredientNotSufficient extends RuntimeException{

    public IngredientNotSufficient(String message) {
        super(message);
    }
}
