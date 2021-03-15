package com.example.demo.beverages.ingrdients;

import com.example.demo.beverages.Beverage;

public class HotWaterIngredient extends IngredientDecorator {
    private String name;
    public HotWaterIngredient(Beverage beverage) {
        super(beverage);
        name = "Hot Water";
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", " + name;
    }
}
