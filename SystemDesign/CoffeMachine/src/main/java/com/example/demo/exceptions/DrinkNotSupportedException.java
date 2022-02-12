package com.example.demo.exceptions;

public class DrinkNotSupportedException extends RuntimeException{

    public DrinkNotSupportedException(String message) {
        super(message);
    }
}
