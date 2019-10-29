package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Usuario;
import com.project.repositories.IUsuarioRepository;



@RestController
@RequestMapping("json")
public class JsonController {
	
	@Autowired
	private IUsuarioRepository usuariosRepo;
	
	@GetMapping("/checkemail")
	public String emailDuplicated(@RequestParam("email") String email){
		
		List<Usuario> listaUsuarios = usuariosRepo.findAll();
		
		boolean isFound = usuariosRepo.existsByEmail(email);
		
		//return isFound;
		
		return "{\"result\": "+isFound+"}";
		
	}
	
}