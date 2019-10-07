package com.project.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.project.controllers" })
@EntityScan("com.project.models")
@EnableJpaRepositories("com.project.repositories")

public class TfgSvpApplication {
	public static void main(String[] args) {
		SpringApplication.run(TfgSvpApplication.class, args);
	}
}
