package com.sun.microservices.product;

import org.springframework.boot.SpringApplication;

public class TestProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProductApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
