package com.example.demo;

import com.example.demo.builders.BeverageBuilder;
import com.example.demo.payloads.GenericResponse;
import com.example.demo.payloads.MachinePayload;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

class CoffeeMachineApplicationTests {

	@Test
	@DisplayName("Make Single Beverage with more ingredients")
	public void pass1() throws ExecutionException, InterruptedException {
		MachinePayload machinePayload = new MachinePayload();
		machinePayload.setOutlets(new HashMap<String, Integer>(){{
			put("count_n", 1);
		}});
		machinePayload.setTotal_items_quantity(new HashMap<String, Integer>(){{
			put("hot_water", 500);
			put("hot_milk", 500);
			put("ginger_syrup", 100);
			put("sugar_syrup", 100);
			put("tea_leaves_syrup", 100);
		}});
		machinePayload.setBeverages(new HashMap<String, Map<String, Integer>>(){{
			put("hot_tea", new HashMap<String, Integer>(){{
				put("hot_water", 200);
				put("hot_milk", 100);
				put("ginger_syrup", 10);
				put("sugar_syrup", 10);
				put("tea_leaves_syrup", 30);
			}});
		}});

		BeverageBuilder beverageBuilder = new BeverageBuilder(machinePayload);
		Map<String , GenericResponse> data = beverageBuilder.build();
		Assertions.assertTrue(data.get("hot_tea").getStatus());
	}

	@Test
	@DisplayName("Make Single Beverage with exact ingredients")
	public void pass2() throws ExecutionException, InterruptedException {
		MachinePayload machinePayload = new MachinePayload();
		machinePayload.setOutlets(new HashMap<String, Integer>(){{
			put("count_n", 1);
		}});
		machinePayload.setTotal_items_quantity(new HashMap<String, Integer>(){{
			put("hot_water", 200);
			put("hot_milk", 100);
			put("ginger_syrup", 10);
			put("sugar_syrup", 10);
			put("tea_leaves_syrup", 30);
		}});
		machinePayload.setBeverages(new HashMap<String, Map<String, Integer>>(){{
			put("hot_tea", new HashMap<String, Integer>(){{
				put("hot_water", 200);
				put("hot_milk", 100);
				put("ginger_syrup", 10);
				put("sugar_syrup", 10);
				put("tea_leaves_syrup", 30);
			}});
		}});

		BeverageBuilder beverageBuilder = new BeverageBuilder(machinePayload);
		Map<String , GenericResponse> data = beverageBuilder.build();
		Assertions.assertTrue(data.get("hot_tea").getStatus());
	}

	@Test
	@DisplayName("Make Single Beverage with Less ingredients")
	public void fail1() throws ExecutionException, InterruptedException {
		MachinePayload machinePayload = new MachinePayload();
		machinePayload.setOutlets(new HashMap<String, Integer>(){{
			put("count_n", 1);
		}});
		machinePayload.setTotal_items_quantity(new HashMap<String, Integer>(){{
			put("hot_water", 10);
			put("hot_milk", 100);
			put("ginger_syrup", 10);
			put("sugar_syrup", 10);
			put("tea_leaves_syrup", 30);
		}});
		machinePayload.setBeverages(new HashMap<String, Map<String, Integer>>(){{
			put("hot_tea", new HashMap<String, Integer>(){{
				put("hot_water", 200);
				put("hot_milk", 100);
				put("ginger_syrup", 10);
				put("sugar_syrup", 10);
				put("tea_leaves_syrup", 30);
			}});
		}});

		BeverageBuilder beverageBuilder = new BeverageBuilder(machinePayload);
		Map<String , GenericResponse> data = beverageBuilder.build();
		Assertions.assertFalse(data.get("hot_tea").getStatus());
		Assertions.assertEquals(data.get("hot_tea").getException().getMessage(), "hot_water is Not Sufficient");
	}

	@Test
	@DisplayName("Make Single Beverage with Unsupported ingredients")
	public void fail2() throws ExecutionException, InterruptedException {
		MachinePayload machinePayload = new MachinePayload();
		machinePayload.setOutlets(new HashMap<String, Integer>(){{
			put("count_n", 1);
		}});
		machinePayload.setTotal_items_quantity(new HashMap<String, Integer>(){{
			put("hot_water", 200);
			put("hot_milk", 100);
			put("ginger_syrup", 10);
			put("sugar_syrup", 10);
			put("tea_leaves_syrup", 30);
		}});
		machinePayload.setBeverages(new HashMap<String, Map<String, Integer>>(){{
			put("green_tea", new HashMap<String, Integer>(){{
				put("hot_water", 200);
				put("hot_milk", 100);
				put("ginger_syrup", 10);
				put("sugar_syrup", 10);
				put("green_mixture", 30);
			}});
		}});

		BeverageBuilder beverageBuilder = new BeverageBuilder(machinePayload);
		Map<String , GenericResponse> data = beverageBuilder.build();
		Assertions.assertFalse(data.get("green_tea").getStatus());
		Assertions.assertEquals(data.get("green_tea").getException().getMessage(), "green_mixture is Not Available");
	}

	@Test
	@DisplayName("Make Unsupported Beverage")
	public void fail3() throws ExecutionException, InterruptedException {
		MachinePayload machinePayload = new MachinePayload();
		machinePayload.setOutlets(new HashMap<String, Integer>(){{
			put("count_n", 1);
		}});
		machinePayload.setTotal_items_quantity(new HashMap<String, Integer>(){{
			put("hot_water", 200);
			put("hot_milk", 100);
			put("ginger_syrup", 10);
			put("sugar_syrup", 10);
			put("tea_leaves_syrup", 30);
		}});
		machinePayload.setBeverages(new HashMap<String, Map<String, Integer>>(){{
			put("special_green_tea", new HashMap<String, Integer>(){{
				put("hot_water", 200);
				put("hot_milk", 100);
				put("ginger_syrup", 10);
				put("sugar_syrup", 10);
				put("special_green_mixture", 30);
			}});
		}});

		BeverageBuilder beverageBuilder = new BeverageBuilder(machinePayload);
		Map<String , GenericResponse> data = beverageBuilder.build();
		Assertions.assertFalse(data.get("special_green_tea").getStatus());
		Assertions.assertEquals(data.get("special_green_tea").getException().getMessage(), "special_green_tea is Not Supported");
	}

}
