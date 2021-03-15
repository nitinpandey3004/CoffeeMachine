package com.example.demo.beverages.ingrdients;

import com.example.demo.beverages.Beverage;

public class ElaichiSyrupIngredient extends IngredientDecorator{
    private String name;
    public ElaichiSyrupIngredient(Beverage beverage) {
        super(beverage);
        name = "Elaichi Syrup";
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", " + name;
    }
}
