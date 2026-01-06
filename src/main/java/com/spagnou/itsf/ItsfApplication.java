package com.spagnou.itsf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the ITSF Spring Boot application.
 * 
 * This class starts the Spring Boot application and initializes the application context.
 */
@SpringBootApplication
public class ItsfApplication {

	 /**
     * Main entry point of the application.
     *
     * @param args command line arguments passed at application startup
     */
	public static void main(String[] args) {
		SpringApplication.run(ItsfApplication.class, args);
	}
	
}
