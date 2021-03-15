package com.example.demo.beverages.drinks;

import com.example.demo.beverages.Beverage;

public class HotTea extends Drink {

    private final String name;

    public HotTea() {
        name = "HotTea";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIngredients() {
        return name;
    }

    @Override
    public void build() {

    }
}

