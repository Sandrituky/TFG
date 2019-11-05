//AUN NO HE USADO ESTO DE SERVICIOS T-T

/*package com.project.services;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


public class UserDetailsService {

	
	@Bean
	public UserDetailsService userDetailsService() throws Exception {
	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	    manager.createUser(User
	      .withUsername("user")
	      .password(encoder().encode("userPass"))
	      .roles("USER")
	      .build());
	     
	    manager.createUser(User
	      .withUsername("admin")
	      .password(encoder().encode("adminPass"))
	      .roles("ADMIN")
	      .build());
	     
	    return manager;
	}
	
	@Bean
	public static PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
}

*/
