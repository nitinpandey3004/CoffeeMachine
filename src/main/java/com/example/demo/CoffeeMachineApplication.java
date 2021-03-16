package com.example.demo;


import com.example.demo.builders.BeverageBuilder;
import com.example.demo.payloads.MachinePayload;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class CoffeeMachineApplication {

	public static void main(String[] args) {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/static/test.json"));
			MachinePayload machinePayload = objectMapper.readValue(jsonNode.get("machine").toString(), MachinePayload.class);
			new BeverageBuilder(machinePayload);
		} catch (InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();
		}
	}

}
