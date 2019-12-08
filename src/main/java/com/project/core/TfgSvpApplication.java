package com.project.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan({ "com.project.controllers", "com.project.config", "com.project.services"})
@EntityScan("com.project.model")
@EnableJpaRepositories("com.project.repositories")

public class TfgSvpApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TfgSvpApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TfgSvpApplication.class, args);
	}
	

	
}
