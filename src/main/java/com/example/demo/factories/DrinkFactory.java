package com.example.demo.factories;

import com.example.demo.beverages.Drink;
import com.example.demo.exceptions.DrinkNotSupportedException;

/**
 * DrinkFactory is used to create base Drinks for beverages
 */
public class DrinkFactory {

    public Drink createDrink(String drinkName) throws DrinkNotSupportedException {
        Drink drink = null;
        switch (drinkName) {
            case "hot_tea" :
            case "hot_coffee" :
            case "black_tea" :
            case "green_tea" :
                drink = new Drink(drinkName);
                break;

            default:
                throw new DrinkNotSupportedException(drinkName + " is Not Supported");
        }
        return drink;
    }
}
