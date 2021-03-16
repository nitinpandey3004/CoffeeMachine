package com.example.demo.builders;

import com.example.demo.factories.DrinkFactory;
import com.example.demo.factories.IngredientFactory;
import com.example.demo.payloads.GenericResponse;
import com.example.demo.payloads.MachinePayload;
import com.example.demo.providers.InMemoryIngredientLockProvider;
import com.example.demo.providers.IngredientLockProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * MachineBuilder is a builder class for building Machine
 */
public class MachineBuilder {
    IngredientLockProvider ingredientLockProvider;
    DrinkFactory drinkFactory;
    IngredientFactory ingredientFactory;
    ExecutorService executorService;
    MachinePayload machinePayload;
    public MachineBuilder(MachinePayload machinePayload)  {
        drinkFactory = new DrinkFactory();
        ingredientFactory = new IngredientFactory();
        this.machinePayload = machinePayload;

        executorService = Executors.newFixedThreadPool(machinePayload.getOutlets().get("count_n"));
        ingredientLockProvider = new InMemoryIngredientLockProvider(machinePayload.getTotal_items_quantity());
    }

    public Map<String , GenericResponse> build() throws ExecutionException, InterruptedException{
        Map<String , GenericResponse> data = createBeverages(machinePayload.getBeverages());
        executorService.shutdown();
        return data;
    }

    private Map<String , GenericResponse> createBeverages(Map<String, Map<String, Integer>> beverages) throws ExecutionException, InterruptedException {
        Map<String , GenericResponse> data = new HashMap<>();
        for(String name: beverages.keySet()) {
            BeverageBuilder beverageBuilder = new BeverageBuilder(name, beverages.get(name),
                    drinkFactory, ingredientFactory, ingredientLockProvider);
            Future<GenericResponse> future = executorService.submit(beverageBuilder);
            data.put(name, future.get());
        }
        return data;
    }
}
