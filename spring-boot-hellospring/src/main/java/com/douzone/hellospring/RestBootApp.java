package com.douzone.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RestBootApp {
	
	@RestController
	public class MyController{
		
		@GetMapping("/hello")
		public String hello() {
			return "Hello World";
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RestBootApp.class, args);
	}
}
