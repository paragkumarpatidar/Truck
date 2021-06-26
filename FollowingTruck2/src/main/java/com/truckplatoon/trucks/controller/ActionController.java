package com.truckplatoon.trucks.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {

	@Value("${spring.application.name}")
	private String applicationName;

	@GetMapping("/action/{action}")
	public String performAction(@PathVariable String action) {
		System.out.println(applicationName + " performed the action " + action);
		return "Success";
	}

}
