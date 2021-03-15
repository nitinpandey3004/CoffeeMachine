package com.example.demo.beverages.drinks;

import com.example.demo.beverages.Beverage;

public class GingerTea extends Beverage {
    private final String name;

    public GingerTea() {
        name = "Ginger tea";
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
