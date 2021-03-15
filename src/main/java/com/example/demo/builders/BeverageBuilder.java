package com.example.demo.builders;

import com.example.demo.factories.DrinkFactory;
import com.example.demo.factories.IngredientFactory;
import com.example.demo.payloads.MachinePayload;
import com.example.demo.providers.InMemoryIngredientLockProvider;
import com.example.demo.providers.IngredientLockProvider;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BeverageBuilder {
    IngredientLockProvider ingredientLockProvider;
    DrinkFactory drinkFactory;
    IngredientFactory ingredientFactory;
    ExecutorService executorService;
    public BeverageBuilder(MachinePayload machinePayload) throws ExecutionException, InterruptedException {
        drinkFactory = new DrinkFactory();
        ingredientFactory = new IngredientFactory();

        initializeExecutors(machinePayload.getOutlets().get("count_n"));
        initializeResources(machinePayload.getTotal_items_quantity());

        createBeverages(machinePayload.getBeverages());
    }

    private void initializeExecutors(Integer n) {
        executorService = Executors.newFixedThreadPool(n);
    }

    private void initializeResources(Map<String, Integer> totalItemsQuantity) {
        ingredientLockProvider = new InMemoryIngredientLockProvider(totalItemsQuantity);
    }

    private void createBeverages(Map<String, Map<String, Integer>> beverages) throws ExecutionException, InterruptedException {

        for(String name: beverages.keySet()) {
            WorkerThread workerThread = new WorkerThread(name, beverages.get(name),
                    drinkFactory, ingredientFactory, ingredientLockProvider);
            Future<String> future = executorService.submit(workerThread);
            System.out.println(future.get());
        }
        executorService.shutdown();
    }
}
