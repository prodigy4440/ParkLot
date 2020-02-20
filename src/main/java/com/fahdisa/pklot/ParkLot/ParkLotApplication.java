package com.fahdisa.pklot.ParkLot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkLotApplication {

	private static final Logger logger = LoggerFactory.getLogger(ParkLotApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ParkLotApplication.class, args);
	}

}
