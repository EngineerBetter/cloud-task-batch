package com.fil.service.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableTask
@EnableBatchProcessing
@ComponentScan(basePackages="com.fil.service.*")
public class ShortLivedMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortLivedMsApplication.class, args);
	}
}
