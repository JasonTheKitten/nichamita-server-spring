package com.nichamita.sprsrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class SprsrvApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprsrvApplication.class, args);
	}

}
