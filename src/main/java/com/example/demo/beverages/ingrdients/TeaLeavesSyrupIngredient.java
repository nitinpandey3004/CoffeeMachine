package com.example.demo.beverages.ingrdients;

import com.example.demo.beverages.Beverage;

public class TeaLeavesSyrupIngredient extends IngredientDecorator{
    private String name;
    public TeaLeavesSyrupIngredient(Beverage beverage) {
        super(beverage);
        name = "Tea Leaves Syrup";
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", " + name;
    }
}
