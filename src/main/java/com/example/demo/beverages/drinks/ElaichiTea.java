package com.example.demo.beverages.drinks;

import com.example.demo.beverages.Beverage;

public class ElaichiTea extends Beverage {

    private final String name;

    public ElaichiTea() {
        name = "Elaichi Tea";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIngredients() {
        return name;
    }
}
