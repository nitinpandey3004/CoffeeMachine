package com.example.demo.providers;

import com.example.demo.beverages.ingrdients.Ingredient;
import com.example.demo.beverages.ingrdients.IngredientDecorator;
import com.example.demo.exceptions.IngredientNotPresentException;

import java.util.List;

public interface IngredientLockProvider {

    boolean lockIngredients(List<Ingredient> ingredients) throws IngredientNotPresentException;

    boolean isIngredientPresent(Ingredient ingredient);
}
