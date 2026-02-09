package com.securityexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SecurityexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityexampleApplication.class, args);
	}

}
