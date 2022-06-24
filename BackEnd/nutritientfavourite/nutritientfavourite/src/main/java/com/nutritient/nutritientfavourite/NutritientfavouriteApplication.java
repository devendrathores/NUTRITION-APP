package com.nutritient.nutritientfavourite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableEurekaClient
public class NutritientfavouriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutritientfavouriteApplication.class, args);
	}
	
	@Bean
	public CorsFilter corsFilter(){
		UrlBasedCorsConfigurationSource src=new UrlBasedCorsConfigurationSource();
		CorsConfiguration config=new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedMethod("*");
		config.addAllowedHeader("*");
		config.addAllowedOriginPattern("*");
		src.registerCorsConfiguration("/**",config);
		return new CorsFilter(src);
	}
}
