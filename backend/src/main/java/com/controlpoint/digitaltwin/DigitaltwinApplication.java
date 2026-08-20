package com.controlpoint.digitaltwin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DigitaltwinApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitaltwinApplication.class, args);
	}

}
