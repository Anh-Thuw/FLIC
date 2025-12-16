package com.flic.courseRegister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Thêm import cho thư viện dotenv-java
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class CourseRegisterApplication {

	public static void main(String[] args) {

		try {

			Dotenv dotenv = Dotenv.load();


			dotenv.entries().forEach(entry ->
					System.setProperty(entry.getKey(), entry.getValue())
			);
			System.out.println(">>> Loaded .env file successfully.");

		} catch (io.github.cdimascio.dotenv.DotenvException e) {

			System.out.println(">>> .env file not found. Assuming environment variables are pre-configured (e.g., in Cloud environment).");
		}

		SpringApplication.run(CourseRegisterApplication.class, args);
	}
}