package com.example.demo.builders;

import com.example.demo.beverages.Beverage;
import com.example.demo.beverages.Ingredient;
import com.example.demo.exceptions.DrinkNotSupportedException;
import com.example.demo.exceptions.IngredientNotAvailableException;
import com.example.demo.exceptions.IngredientNotSufficient;
import com.example.demo.factories.DrinkFactory;
import com.example.demo.factories.IngredientFactory;
import com.example.demo.payloads.GenericResponse;
import com.example.demo.providers.IngredientLockProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * BeverageBuilder is Basic unit for making a Beverage
 * This class handles all the exception and generate generic response
 */
public class BeverageBuilder implements Callable<GenericResponse> {
    private String name;
    private Map<String, Integer> ingredients;
    private DrinkFactory drinkFactory;
    private IngredientFactory ingredientFactory;
    private IngredientLockProvider ingredientLockProvider;

    public BeverageBuilder(String name, Map<String , Integer> ingredients, DrinkFactory drinkFactory,
                           IngredientFactory ingredientFactory, IngredientLockProvider ingredientLockProvider) {
        this.name = name;
        this.ingredients = ingredients;
        this.drinkFactory = drinkFactory;
        this.ingredientFactory = ingredientFactory;
        this.ingredientLockProvider = ingredientLockProvider;
    }

    @Override
    public GenericResponse call() {
        Beverage drink;
        try {
             drink = drinkFactory.createDrink(name);
        } catch (DrinkNotSupportedException e) {
            return new GenericResponse(false, "", e);
        }
        List<Ingredient> ingredientList = new ArrayList<>();
        for(String ingredientName: ingredients.keySet()) {
            try {
                Ingredient ingredient = ingredientFactory.getIngredient(drink, ingredientName, ingredients.get(ingredientName));
                ingredientList.add(ingredient);
            } catch (IngredientNotAvailableException e) {
                return new GenericResponse(false, "", e);
            }
        }

        try {
            ingredientLockProvider.lockIngredients(ingredientList);
        } catch (IngredientNotSufficient e) {
            return new GenericResponse(false, "", e);
        }

        for(Ingredient ingredient: ingredientList) {
            drink = new Ingredient(drink, ingredient.getName(), ingredient.getQuantity());
        }
        return new GenericResponse(true, drink.getIngredients(), null);
    }
}
