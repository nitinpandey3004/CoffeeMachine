package com.example.demo.providers;

import com.example.demo.beverages.Ingredient;
import com.example.demo.exceptions.IngredientNotSufficient;
import java.util.List;

/**
 * IngredientLockProvider is an interface for defining Ingredient Lock Interface
 */
public interface IngredientLockProvider {

    boolean lockIngredients(List<Ingredient> ingredients) throws IngredientNotSufficient;

    boolean isIngredientPresent(Ingredient ingredient);
}
