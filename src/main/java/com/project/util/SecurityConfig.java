package com.project.util;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	


  //Necesario para evitar que la seguridad se aplique a los resources
  //Como los css, imagenes y javascripts
  String[] resources = new String[]{
          "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
  };
  
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	       return super.authenticationManagerBean();
	}
  
	@Bean
  @Override
  public UserDetailsService userDetailsService() {
  	
  	UserDetails user=User.builder().username("user")
                      .password(passwordEncoder().encode("secret"))
  			.roles("USER").build();
  	UserDetails userAdmin = User.builder().username("admin")
                  .password(passwordEncoder().encode("secret"))
  		    .roles("ADMIN").build();
      return new InMemoryUserDetailsManager(user,userAdmin);
  }
	
  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http
          .authorizeRequests()
        .antMatchers(resources).permitAll()  
        .antMatchers("/","/index").permitAll()
        .antMatchers("animales/altaAnimal","animales/bajaAnimal","animales/modAnimal").access("hasRole('ADMIN')")
        .antMatchers("/user*").access("hasRole('USER') or hasRole('ADMIN')")
              .anyRequest().authenticated()
              .and()
          .formLogin()
              .loginPage("/login")
              .permitAll()
              .defaultSuccessUrl("/")
              .failureUrl("")
              .usernameParameter("loginEmail") //name="email" (form)
              .passwordParameter("loginPassword") //name="password" (form)
              .and()
          .logout()
              .permitAll()
              .logoutSuccessUrl("/login?logout");
  }
  

  
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
	
	
}
