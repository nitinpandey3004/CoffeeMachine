package com.example.demo.beverages;

/**
 * Beverage is abstract class for Decorator Pattern.
 * Drink class and IngredientDecorator class extends this class for Creating Beverages of different types.
 * There are two main types of Beverage
 *  1. Main Drink (like Tea, Coffee)
 *  2. Supporting ingredients (like sugar syrup)
 */
public abstract class Beverage {

    public abstract String getName();

    public abstract String getIngredients();
}