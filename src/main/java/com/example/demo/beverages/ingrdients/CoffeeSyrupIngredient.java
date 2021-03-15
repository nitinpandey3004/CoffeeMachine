package com.example.demo.beverages.ingrdients;

import com.example.demo.beverages.Beverage;

public class CoffeeSyrupIngredient extends IngredientDecorator{
    private String name;
    public CoffeeSyrupIngredient(Beverage beverage) {
        super(beverage);
        name = "Coffee Syrup";
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", " + name;
    }
}
