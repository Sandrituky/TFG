package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Animal;
import com.project.model.Esterilizado;
import com.project.model.Provincia;
import com.project.model.Sexo;
import com.project.model.Tipo;
import com.project.model.Usuario;
import com.project.repositories.IUsuarioRepository;
import com.project.repositories.IAnimalRepository;
import com.project.repositories.IProvinciaRepository;



@RestController
@RequestMapping("json")
public class JsonController {
	
	@Autowired
	private IUsuarioRepository usuariosRepo;
	
	@Autowired
	private IAnimalRepository animalesRepo;
	
	@Autowired
	private IProvinciaRepository provinciasRepo;
	
	@GetMapping("/checkemail")
	public String duplicatedEmail(@RequestParam("email") String email){
		
		List<Usuario> listaUsuarios = usuariosRepo.findAll();
		
		boolean isFound = usuariosRepo.existsByEmail(email);
		
		//return isFound;
		
		return "{\"result\": "+isFound+"}";
		
	}
	
	
	@GetMapping("/checkdni")
	public String duplicatedDNI(@RequestParam("dni") String dni){
		
		List<Usuario> listaUsuarios = usuariosRepo.findAll();
		
		boolean isFound = usuariosRepo.existsByDni(dni);
		
		//return isFound;
		
		return "{\"result\": "+isFound+"}";
		
	}
	
	@GetMapping("/checktelefono")
	public String duplicatedPhone(@RequestParam("telefono") String telefono){
		
		List<Usuario> listaUsuarios = usuariosRepo.findAll();
		
		boolean isFound = usuariosRepo.existsByTelefono(telefono);
		
		//return isFound;
		
		return "{\"result\": "+isFound+"}";
		
	}
	

	
}