package com.example.demo.beverages.ingrdients;

import com.example.demo.beverages.Beverage;

public class GingerSyrupIngredient extends IngredientDecorator{
    private String name;
    public GingerSyrupIngredient(Beverage beverage) {
        super(beverage);
        name = "Ginger Syrup";
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", " + name;
    }
}
