package com.truckplatoon.trucks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truckplatoon.trucks.model.Truck;

public interface TruckRepository extends JpaRepository<Truck, Long> {

}
