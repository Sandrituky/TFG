package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.ui.Model;

import com.project.model.Animal;
import com.project.model.Provincia;
import com.project.model.Tipo;
import com.project.model.Usuario;
import com.project.model.Sexo;
import com.project.model.Estado;
import com.project.model.Esterilizado;
import com.project.repositories.IAnimalRepository;
import com.project.repositories.IProvinciaRepository;
import com.project.repositories.IUsuarioRepository;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	
	@Autowired
	private IUsuarioRepository usuariosRepo;
	
	@Autowired
	private IProvinciaRepository provinciasRepo;
	
	@GetMapping("/altaUsuario")//
	public String pageAdd(Model model) {
		 
		Usuario user = new Usuario();
		List<Provincia> listaProvincias = provinciasRepo.findAll();
		
		
	 	model.addAttribute("usuario", user);
	 	model.addAttribute("provincias", listaProvincias); 

	 	
	      return "usuarios/altaUsuario";
	}
	
	
	
}
