package com.example.demo.beverages.ingrdients;

import com.example.demo.beverages.Beverage;

public class HotMilkIngredient extends IngredientDecorator {
    private String name;
    public HotMilkIngredient(Beverage beverage) {
        super(beverage);
        name = "Hot Milk";
    }

    public String getIngredients() {
        return super.getIngredients() + ", " + name;
    }
}
