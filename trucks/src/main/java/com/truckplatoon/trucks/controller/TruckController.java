package com.truckplatoon.trucks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truckplatoon.trucks.model.Truck;
import com.truckplatoon.trucks.repository.TruckRepository;

@RestController
@RequestMapping("/trucks")
public class TruckController {

	@Autowired
	private TruckRepository truckRepository;

	@GetMapping("/")
	public List<Truck> getAllTrucks() {
		return truckRepository.findAll();
	}

	@GetMapping("/{id}")
	public Truck getTruckFromId(@PathVariable(value = "id") Integer truckId) throws ResourceNotFoundException {
		return truckRepository.findById(truckId)
				.orElseThrow(() -> new ResourceNotFoundException("Truck not found for this id :: " + truckId));
	}

	@PostMapping("/addTruck")
	public Truck createTruck(@Validated @RequestBody Truck truck) {
		return truckRepository.save(truck);
	}

	@DeleteMapping("/deleteTruck/{id}")
	public Map<String, Boolean> deleteTruck(@PathVariable(value = "id") Integer truckId) throws ResourceNotFoundException {
		Truck truck = truckRepository.findById(truckId)
				.orElseThrow(() -> new ResourceNotFoundException("Truck not found for this id :: " + truckId));

		truckRepository.delete(truck);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
