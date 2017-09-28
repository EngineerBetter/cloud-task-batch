package com.fil.service.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShortLivedMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchJobConfiguration.class, args);
	}
}
