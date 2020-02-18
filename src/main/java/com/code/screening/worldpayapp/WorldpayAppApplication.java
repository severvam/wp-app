package com.code.screening.worldpayapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WorldpayAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorldpayAppApplication.class, args);
	}

}
