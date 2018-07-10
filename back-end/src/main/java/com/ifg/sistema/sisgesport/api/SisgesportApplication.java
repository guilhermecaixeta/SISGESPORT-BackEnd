package com.ifg.sistema.sisgesport.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SisgesportApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisgesportApplication.class, args);
	}
}
