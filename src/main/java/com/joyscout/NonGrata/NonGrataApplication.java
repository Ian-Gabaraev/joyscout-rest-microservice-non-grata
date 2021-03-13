package com.joyscout.NonGrata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NonGrataApplication {

	private static final Logger logger = LoggerFactory.getLogger(NonGrataApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NonGrataApplication.class, args);
		logger.info("NonGrata microservice started successfully");
	}
}
