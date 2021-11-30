package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication

public class MovieRestfullWebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieRestfullWebservicesApplication.class, args);
	}
     
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	
}
