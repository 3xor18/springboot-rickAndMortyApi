package com.xor18.rickAndMortyApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class RickAndMortyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RickAndMortyApiApplication.class, args);
	}

}
