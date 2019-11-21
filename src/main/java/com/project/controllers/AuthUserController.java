package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.project.model.Usuario;
import com.project.repositories.IUsuarioRepository;


@ControllerAdvice
public class AuthUserController { //GlobalControllerAdvice
	
	@Autowired
	IUsuarioRepository userRepository;

	@ModelAttribute
	public void sendAuthUserToView(Model model) {
		model.addAttribute("authUser", this.getAuthUser());
		
	}
	
	public Usuario getAuthUser() { //devuelve el usuario logeado
		Usuario usuario = null;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {		    
			try {
				usuario = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));    
			}
			catch (Exception e) {
				System.out.println(e); 
			}
		}
    
		return usuario;
	}
}