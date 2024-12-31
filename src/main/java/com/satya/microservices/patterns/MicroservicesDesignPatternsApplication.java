package com.satya.microservices.patterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.satya.microservices.patterns.sec08")
public class MicroservicesDesignPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesDesignPatternsApplication.class, args);
	}

}
