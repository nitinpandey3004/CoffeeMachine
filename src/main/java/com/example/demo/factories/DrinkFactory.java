package com.example.demo.factories;

import com.example.demo.beverages.drinks.Drink;
import com.example.demo.beverages.drinks.HotTea;
import com.example.demo.enums.DrinkType;
import com.example.demo.exceptions.DrinkNotSupportedException;

public class DrinkFactory {

    public Drink createDrink(DrinkType drinkType) throws DrinkNotSupportedException {
        Drink drink = null;
        switch (drinkType) {
            case HotTea :
                drink = new HotTea();
                break;

            default:
                throw new DrinkNotSupportedException();
        }
        return drink;
    }
}
