package com.indigo.flightstatus.flightstatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FlightstatusApplication {

	public static void main(String[] args) {
		System.out.println("Flight Status Application started");
		SpringApplication.run(FlightstatusApplication.class, args);
	}

}
