package com.example.demo.beverages.ingrdients;

import com.example.demo.beverages.Beverage;

public class Ingredient  extends IngredientDecorator{
    private String name;
    private Integer quantity;
    public Ingredient(Beverage beverage, String name, Integer quantity) {
        super(beverage);
        this.name = name;
        this.quantity = quantity;
    }

    public Integer getQuantity()  {
        return quantity;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", " + name;
    }
}
