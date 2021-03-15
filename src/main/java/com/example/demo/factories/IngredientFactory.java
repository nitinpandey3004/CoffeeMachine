package com.example.demo.factories;

import com.example.demo.beverages.Beverage;
import com.example.demo.beverages.ingrdients.HotMilkIngredient;
import com.example.demo.beverages.ingrdients.IngredientDecorator;
import com.example.demo.enums.IngredientType;
import com.example.demo.exceptions.IngredientNotSupportedException;

public class IngredientFactory {

    public IngredientDecorator getIngredient(Beverage beverage, IngredientType ingredientType) throws IngredientNotSupportedException {
        IngredientDecorator ingredientDecorator = null;

        switch (ingredientType) {
            case HotMilk :
                ingredientDecorator = new HotMilkIngredient(beverage);
                break;
            default:
                throw new IngredientNotSupportedException();
        }

        return ingredientDecorator;
    }
}
