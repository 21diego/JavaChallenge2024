package com.javashark.puntosdeventaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class PuntosDeVentaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PuntosDeVentaApiApplication.class, args);
	}

}
