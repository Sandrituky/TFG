package com.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport{

	 @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
             .addResourceLocations("classpath:/static/");
        
        registry.addResourceHandler("/imagenes/**").addResourceLocations("file:imagenes/");
        registry.addResourceHandler("/imagenes/animales/**").addResourceLocations("file:imagenes/animales/");
     
    }
	 

}
