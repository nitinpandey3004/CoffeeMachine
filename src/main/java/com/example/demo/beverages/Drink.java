package com.example.demo.beverages;

import com.example.demo.beverages.Beverage;

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
