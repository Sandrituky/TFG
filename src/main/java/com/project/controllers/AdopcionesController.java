package com.project.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.project.model.Animal;
import com.project.model.Estado;
import com.project.model.Esterilizado;
import com.project.model.Sexo;
import com.project.model.Tipo;
import com.project.model.Usuario;
import com.project.repositories.IAnimalRepository;
import com.project.repositories.IProvinciaRepository;
import com.project.repositories.IUsuarioRepository;
import com.project.services.UserDetailsServiceImpl;

@Controller
@RequestMapping("/animales")
public class AdopcionesController {
	
	@Autowired
	private IAnimalRepository animalesRepo;

	@Autowired
	private IProvinciaRepository provinciasRepo;

	@Autowired
	private IUsuarioRepository usuariosRepo;
	
	@Autowired
	private AuthUserController authController;
	
	
	@PostMapping("/reservar-submit") //Reservar (done by Usuario)
	public RedirectView reservarAnimal(Animal animal, Model model,
			RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {

		
		Usuario usuario = authController.getAuthUser();
		
		//Optional <Animal> currentAnimal = animalesRepo.findOneAnimalById(animal.getId());
		Animal currentAnimal = animalesRepo.findAnimalById(animal.getId());

		if (currentAnimal.getOwner() == null && currentAnimal.getEstado() == Estado.EN_ADOPCION) {
			currentAnimal.setOwner(usuario);
			currentAnimal.setEstado(Estado.RESERVADO);
			animalesRepo.save(currentAnimal);
		}else {
			redirectAttributes.addFlashAttribute("message",
					"Éste animal no está disponible para su reserva.");
		}
		
		return new RedirectView("../");
	}
	

}
