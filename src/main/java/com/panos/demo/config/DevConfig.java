package com.panos.demo.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class DevConfig {
	
	@PostConstruct
	public void test() {
		System.out.println("DEV enviroment loaded");
	}
}
