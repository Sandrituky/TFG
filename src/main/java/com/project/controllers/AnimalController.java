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
@RequestMapping("/animales")
public class AnimalController {

	@Autowired
	private IAnimalRepository animalesRepo;
	
	@Autowired
	private IProvinciaRepository provinciasRepo;
	
	@Autowired
	private IUsuarioRepository usuariosRepo;
	
	@GetMapping("/list")
	public String pageList(Model model) {
	
			List<Animal> listaAnimales = animalesRepo.findAll();
			//Tipo[] opcionesTipo = Tipo.values();
			
			
	    	model.addAttribute("animales", listaAnimales);
	    	model.addAttribute("tipos", Tipo.class);
		 
	    	return "animales/list";
	}
	
	@GetMapping("/altaAnimal")//
	public String pageAdd(Model model) {
		 
		Animal animal = new Animal();
		
		//Sexo[] opcionesSexo = Sexo.values(); No usados
		//Tipo[] opcionesTipo = Tipo.values();
		
	
		Esterilizado[] opcionesEsterilizado = Esterilizado.values(); //esto se envía al foreach de la vista
	
		List<Provincia> listaProvincias = provinciasRepo.findAll();
		List<Usuario> listaUsuarios = usuariosRepo.findAll();
		
		//si fuera un select, se pondría de esta forma: 
		//model.addAttribute("tipos", opcionesTipo);
		
	 	model.addAttribute("animal", animal);
	 	//model.addAttribute("tipos", Tipo.class);
	 	//model.addAttribute("sexos", Sexo.class);
	 	model.addAttribute("provincias", listaProvincias); 
	 	model.addAttribute("esterilizados", opcionesEsterilizado);
	 	
	      return "animales/altaAnimal";
	}
	
	
	@GetMapping("/bajaAnimal")
	public String redBaja(Model baja) {
		return "animales/bajaAnimal";
	}
	
	@GetMapping("/modAnimal")
	public String redMod(Model mod) {
		return "animales/modAnimal";
	}
	
	@PostMapping("/add-submit")
	public RedirectView pageAddSubmit(Animal animal, Model model) {
	 		
		animalesRepo.save(animal);
		
	 	return new RedirectView("animales/add-submit");
	}
}
