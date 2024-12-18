package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "controller," + "model," + "services")
public class BreakableToy1BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BreakableToy1BackendApplication.class, args);

	}

}
