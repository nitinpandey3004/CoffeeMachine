package com.example.demo.builders;

import com.example.demo.beverages.Beverage;
import com.example.demo.beverages.ingrdients.Ingredient;
import com.example.demo.providers.InMemoryIngredientLockProvider;
import com.example.demo.providers.IngredientLockProvider;

import java.util.List;

public class BeverageBuilder {
    IngredientLockProvider ingredientLockProvider;
    public BeverageBuilder() {
        ingredientLockProvider = new InMemoryIngredientLockProvider();
    }

    public void build(String name, List<Ingredient> ingredients) {
//        Beverage beverage = new Drin
    }
}
