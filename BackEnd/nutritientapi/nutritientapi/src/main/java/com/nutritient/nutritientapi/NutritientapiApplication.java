package com.nutritient.nutritientapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NutritientapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutritientapiApplication.class, args);
	}

}
