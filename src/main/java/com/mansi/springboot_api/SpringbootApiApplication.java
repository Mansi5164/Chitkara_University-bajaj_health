package com.mansi.springboot_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.mansi.springboot_api")
public class SpringbootApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApiApplication.class, args);
	}
}
