package com.example.demo.providers;

import com.example.demo.beverages.Ingredient;
import com.example.demo.exceptions.IngredientNotSufficient;

import java.util.List;
import java.util.Map;

public class InMemoryIngredientLockProvider implements IngredientLockProvider{

    Map<String, Integer> resources;

    public InMemoryIngredientLockProvider(Map<String, Integer> resources) {
        this.resources = resources;
    }

    @Override
    synchronized public boolean lockIngredients(List<Ingredient> ingredients) throws IngredientNotSufficient {
        for(Ingredient ingredient: ingredients) {
            if(!isIngredientPresent(ingredient)) {
                throw new IngredientNotSufficient(ingredient.getName() + " is Not Sufficient");
            }
        }
        for(Ingredient ingredient: ingredients) {
            useIngredient(ingredient);
        }
        return false;
    }

    public void useIngredient(Ingredient ingredient) {
        resources.put(ingredient.getName(), resources.get(ingredient.getName()) - ingredient.getQuantity());
    }

    @Override
    public boolean isIngredientPresent(Ingredient ingredient) {
        if(!resources.containsKey(ingredient.getName())) {
            return false;
        }
        return resources.get(ingredient.getName()) >= ingredient.getQuantity();
    }
}
