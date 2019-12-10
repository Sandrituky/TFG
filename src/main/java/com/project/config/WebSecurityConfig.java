package com.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.project.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	
    String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/imagenes/**","/js/**","/plugins/**","/layer/**"
    };
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
	        .antMatchers(resources).permitAll()  
	        .antMatchers("/","/index","/login").permitAll()
	        .antMatchers("/usuarios/*").permitAll()
	        .antMatchers("/usuarios/reservas").access("hasAuthority('USER')")
	        .antMatchers("/animales/list**").permitAll()
	        .antMatchers("/json/**").permitAll()
	        .antMatchers("/animales/altaAnimal", "/animales/bajaAnimal", "/animales/modAnimal").access("hasAuthority('ADMIN')")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/?re=login")
                .failureUrl("/?re=error")
                .usernameParameter("loginEmail")//name="loginEmail" (form)
                .passwordParameter("loginPassword")//name="loginPassword" (form)
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/?re=logout");
    }
        
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }
	
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImp;
	   
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
    	
        auth.userDetailsService(userDetailsServiceImp).passwordEncoder(passwordEncoder());     
    }
    
}
