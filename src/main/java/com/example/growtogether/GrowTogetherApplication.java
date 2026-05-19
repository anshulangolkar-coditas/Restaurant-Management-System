package com.example.growtogether;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GrowTogetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrowTogetherApplication.class, args);
	}

}
