package com.truckplatoon.trucks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truckplatoon.trucks.model.Truck;
import com.truckplatoon.trucks.repository.TruckRepository;

@RestController
@RequestMapping("/action")
public class TruckActionController {

	@Autowired
	private TruckRepository repository;

	@GetMapping("{action}/{id}")
	public void startFollowing(@PathVariable(name = "id") Integer truckId, @PathVariable String action)
			throws InterruptedException {

		Truck truck = repository.findById(truckId).orElseThrow(() -> new ResourceNotFoundException());
		if ("Leading".equals(truck.getType())) {
			System.out.println(action.toUpperCase() + " performed by Leading " + truck.getName());
			Thread.sleep(2);
			List<Truck> trucks = repository.findAll();
			for (Truck t : trucks) {
				if (t.getId() != truck.getId())
					System.out.println(action.toUpperCase() + " performed on " + t.getName() + " by Leading truck");
			}

		} else {
			System.out.println("Not a leading Truck");
		}

	}

}
