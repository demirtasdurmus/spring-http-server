package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		/**
		 * Check for env variable 'DISABLE_DEVTOOLS' update system accordingly
		 */
		if (System.getenv("DISABLE_DEVTOOLS") != null || System.getProperty("disable.devtools") != null) {
			System.setProperty("spring.devtools.restart.enabled", "false");
		}

		SpringApplication.run(DemoApplication.class, args);
	}
}
