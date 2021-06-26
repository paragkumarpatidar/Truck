package com.truckplatoon.trucks.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RestController
@RequestMapping("/action")
public class TruckActionController {

	private static List<String> followingTrucks;

	static {
		followingTrucks = new ArrayList<>();
		followingTrucks.add("followingTruck1");
		followingTrucks.add("followingTruck2");
	}

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/{action}")
	public String performAction(@PathVariable String action) throws Exception {
		System.out.println("Leading Truck performed action " + action.toUpperCase());
		for (String truck : followingTrucks) {
			Application application = eurekaClient.getApplication(truck);
			InstanceInfo instanceInfo = application.getInstances().get(0);
			String url = getFollowingTruckUrl(instanceInfo.getIPAddr(), instanceInfo.getPort(), action.toUpperCase());
			String response = restTemplate.getForObject(url, String.class);
			System.out.println(response + " : " + truck + " triggered with action " + action.toUpperCase());
		}
		return action.toUpperCase() + " Action performed Successfully";
	}

	private String getFollowingTruckUrl(String ipAddr, int port, String action) {
		return "http://" + ipAddr + ":" + port + "/action/" + action;
	}

}
