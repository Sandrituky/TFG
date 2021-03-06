package com.project.services;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.model.Usuario;
import com.project.repositories.IUsuarioRepository;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUsuarioRepository userRepository;
	
    //@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {		
		
		Usuario appUser = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
				 
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
	

		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appUser.getRol().getRol());
		grantList.add(grantedAuthority);
		
		UserDetails user = (UserDetails) new User(appUser.getEmail(), appUser.getPassword(), grantList);
		
	    return user;
	}
	    
}

