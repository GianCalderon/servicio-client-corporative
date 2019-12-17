package com.springboot.corporative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioCorporativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioCorporativeApplication.class, args);
	}

}
