package com.servicio_persona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.servicio_persona"})
public class ServicioPersonaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioPersonaApplication.class, args);
	}

}
