package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.model.Animal;
import com.project.model.Estado;
import com.project.model.Esterilizado;
import com.project.model.Sexo;
import com.project.model.Tipo;
import com.project.repositories.IAnimalRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;



@Controller
public class MainController {
	
	
	@Autowired
	private IAnimalRepository animalesRepo;

	@GetMapping({"/","/login","/index"})
	public String redIndex(@RequestParam(defaultValue = "") String re, Model model) {
			model.addAttribute("result", re);
			
			//Se cargará en el index una lista con todos los animales en adopción
			List <Animal> listaAnimales = animalesRepo.findByEstadoOrderByFechaAltaDesc(Estado.EN_ADOPCION);

			model.addAttribute("animales", listaAnimales);
			model.addAttribute("tipos", Tipo.class);
			model.addAttribute("sexos", Sexo.class);
			model.addAttribute("esterilizados", Esterilizado.class);
			        
			return "index";
		}
	

	

	


}