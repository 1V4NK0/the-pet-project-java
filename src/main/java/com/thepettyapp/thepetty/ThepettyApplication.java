package com.thepettyapp.thepetty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ThepettyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThepettyApplication.class, args);
	}

}
