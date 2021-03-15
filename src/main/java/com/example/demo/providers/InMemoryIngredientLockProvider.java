package com.example.demo.providers;

import com.example.demo.beverages.ingrdients.Ingredient;
import com.example.demo.exceptions.IngredientNotPresentException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryIngredientLockProvider implements IngredientLockProvider{

    Map<String, Integer> locks;

    public InMemoryIngredientLockProvider() {
        locks =  new HashMap<>();
    }

    @Override
    synchronized public boolean lockIngredients(List<Ingredient> ingredients) throws IngredientNotPresentException {
        for(Ingredient ingredient: ingredients) {
            if(!isIngredientPresent(ingredient)) {
                throw new IngredientNotPresentException();
            }
        }
        for(Ingredient ingredient: ingredients) {
            useIngredient(ingredient);
        }
        return false;
    }

    public void useIngredient(Ingredient ingredient) {
        locks.put(ingredient.getName(), locks.get(ingredient.getName()) - ingredient.getQuantity());
    }

    @Override
    public boolean isIngredientPresent(Ingredient ingredient) {
        if(!locks.containsKey(ingredient.getName())) {
            return false;
        }
        return locks.get(ingredient.getName()) > ingredient.getQuantity();
    }
}
