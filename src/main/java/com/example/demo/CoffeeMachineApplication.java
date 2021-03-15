package com.example.demo;

import com.example.demo.beverages.Beverage;
import com.example.demo.beverages.drinks.GingerTea;
import com.example.demo.beverages.ingrdients.GingerSyrupIngredient;
import com.example.demo.beverages.ingrdients.HotMilkIngredient;


public class CoffeeMachineApplication {

	public static void main(String[] args) {
		Beverage gingerTea = new GingerTea();
		gingerTea = new HotMilkIngredient(gingerTea);
		gingerTea = new GingerSyrupIngredient(gingerTea);

		System.out.println(gingerTea.getIngredients());
	}

}
