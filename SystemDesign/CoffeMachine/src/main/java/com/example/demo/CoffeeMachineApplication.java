package com.example.demo;


import com.example.demo.builders.MachineBuilder;
import com.example.demo.payloads.GenericResponse;
import com.example.demo.payloads.MachinePayload;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class CoffeeMachineApplication {

	public static void main(String[] args) {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/static/sample.json"));
			MachinePayload machinePayload = objectMapper.readValue(jsonNode.get("machine").toString(), MachinePayload.class);
			MachineBuilder machineBuilder = new MachineBuilder(machinePayload);
			Map<String , GenericResponse> data = machineBuilder.build();
			printData(data);
		} catch (InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void printData(Map<String, GenericResponse> data) {

		for(String drinkName: data.keySet()) {
			GenericResponse genericResponse = data.get(drinkName);
			if(genericResponse.getStatus()) {
				System.out.println(drinkName + " is Prepared With " + genericResponse.getMessage());
			} else {
				System.out.println(drinkName + " Cannot Prepared Because " + genericResponse.getException().getMessage());
			}
		}
	}

}
