package com.example.demo.factories;

import com.example.demo.beverages.Beverage;
import com.example.demo.beverages.Ingredient;
import com.example.demo.exceptions.IngredientNotAvailableException;

public class IngredientFactory {

    public Ingredient getIngredient(Beverage beverage, String ingredientName, Integer quantity) throws IngredientNotAvailableException {
        Ingredient ingredient = null;

        switch (ingredientName) {
            case "hot_water" :
            case "hot_milk" :
            case "ginger_syrup" :
            case "sugar_syrup" :
            case "tea_leaves_syrup" :
                ingredient = new Ingredient(beverage, ingredientName, quantity);
                break;
            default:
                throw new IngredientNotAvailableException(ingredientName + " is Not Available");
        }

        return ingredient;
    }
}
