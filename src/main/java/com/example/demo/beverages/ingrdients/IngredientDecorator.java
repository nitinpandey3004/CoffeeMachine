package com.example.demo.beverages.ingrdients;

import com.example.demo.beverages.Beverage;

public abstract class IngredientDecorator extends Beverage {
    private Beverage beverage;
    public IngredientDecorator(Beverage beverage)  {
        this.beverage =  beverage;
    }

    public String getIngredients() {
        return beverage.getIngredients();
    }
}
