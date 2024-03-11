package com.gdj.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GdjBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(GdjBootApplication.class, args);
	}

}
