package com.greedy.woodong.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@ComponentScan("com.greedy.woodong")
public class WoodongProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WoodongProjectApplication.class, args);
	}
	   
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
	   return new HiddenHttpMethodFilter();
	}
}
