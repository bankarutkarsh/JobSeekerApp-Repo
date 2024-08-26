package com.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JobmoduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobmoduleApplication.class, args);
	}	
}
