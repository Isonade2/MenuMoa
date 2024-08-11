package com.wku.menumoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MenumoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenumoaApplication.class, args);
	}
}
