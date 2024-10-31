package com.colegio.colegio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ColegioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColegioApplication.class, args);
	}

}
