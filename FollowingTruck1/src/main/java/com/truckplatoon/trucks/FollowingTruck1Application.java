package com.truckplatoon.trucks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FollowingTruck1Application {

	public static void main(String[] args) {
		SpringApplication.run(FollowingTruck1Application.class, args);
	}

}
