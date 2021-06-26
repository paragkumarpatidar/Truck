package com.truckplatoon.trucks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TruckDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TruckDiscoveryApplication.class, args);
	}

}
