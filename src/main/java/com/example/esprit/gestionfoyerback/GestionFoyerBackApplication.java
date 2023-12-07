package com.example.esprit.gestionfoyerback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestionFoyerBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionFoyerBackApplication.class, args);
	}

}
