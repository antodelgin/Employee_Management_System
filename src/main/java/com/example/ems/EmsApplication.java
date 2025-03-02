package com.example.ems;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Employee Management Application.
 */
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Employee Management Application",
				description = "This application provides a CRUD API for managing employees, including adding, updating, viewing, and deleting employee records.",
				version = "v1.0",
				contact = @Contact(
						name = "Anto Delgin Anston",
						email = "antodelgin@gmail.com",
						url = "https://www.google.com"
				)
		)
)
public class EmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsApplication.class, args);
	}

}
