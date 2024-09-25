package com.iamcaye.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableJdbcRepositories()
public class UserServiceApplication {

	public static void main(String[] args) {
		Dotenv.load();
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
