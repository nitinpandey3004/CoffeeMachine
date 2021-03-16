package com.example.demo.beverages;

/**
 * Drink is a base type of beverage
 * This class is used to create base beverages
 */
public class Drink extends Beverage {

    private String name;
    public Drink(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIngredients() {
        return "";
    }
}
