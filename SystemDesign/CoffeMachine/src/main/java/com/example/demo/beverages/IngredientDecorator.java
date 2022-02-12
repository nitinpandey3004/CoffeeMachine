package com.example.demo.beverages;

/**
 * IngredientDecorator is used for defining abstract class for decorator pattern
 * We take base beverage and decorate it with ingredients
 */
public abstract class IngredientDecorator extends Beverage {
    private Beverage beverage;
    public IngredientDecorator(Beverage beverage)  {
        this.beverage =  beverage;
    }

    public String getIngredients() {
        return beverage.getIngredients();
    }
}
