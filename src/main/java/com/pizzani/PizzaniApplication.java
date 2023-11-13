package com.pizzani;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PizzaniApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaniApplication.class, args);
	}

}
