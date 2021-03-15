package com.example.demo.beverages.ingrdients;

import com.example.demo.beverages.Beverage;

public class SugarSyrupIngredient extends IngredientDecorator{
    private String name;
    public SugarSyrupIngredient(Beverage beverage) {
        super(beverage);
        name = "Sugar Syrup";
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", " + name;
    }
}
