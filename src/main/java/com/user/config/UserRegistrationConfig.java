package com.user.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan({ "com.user.*"})
public class UserRegistrationConfig {

	public static void main(String args[]) {
		SpringApplication.run(UserRegistrationConfig.class, args);
	}

}
