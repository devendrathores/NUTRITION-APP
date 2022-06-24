package com.eurekaserver.nutrition_backened;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NutritionBackenedApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutritionBackenedApplication.class, args);
	}

}
