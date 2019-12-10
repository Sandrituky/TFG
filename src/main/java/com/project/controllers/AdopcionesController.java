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
		
		Animal currentAnimal = animalesRepo.findAnimalById(animal.getId());
		
		int numReservas = animalesRepo.countByOwnerAndEstado(usuario, Estado.RESERVADO);
		
		
		if (currentAnimal.getOwner() == null && currentAnimal.getEstado() == Estado.EN_ADOPCION && numReservas < 3) {
			currentAnimal.setOwner(usuario);
			currentAnimal.setEstado(Estado.RESERVADO);
			animalesRepo.save(currentAnimal);
			redirectAttributes.addFlashAttribute("message",
					"Has reservado a "+currentAnimal.getNombre()).addFlashAttribute("resul","success");
		}else if(numReservas>=3) {
			redirectAttributes.addFlashAttribute("message", "Como máximo puedes reservar hasta 3 animales.").addFlashAttribute("resul","danger");
		}else {
			redirectAttributes.addFlashAttribute("message",
					"Éste animal no está disponible para su reserva.").addFlashAttribute("resul","danger");
		}
		
		return new RedirectView("/usuarios/reservas"); 
	}
	
	@PostMapping("/cancelarReserva-submit") //Cancelar reserva (done by Usuario)
	public RedirectView calcelarReservaAnimal(Animal animal, Model model,
			RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {

		
		Usuario usuario = authController.getAuthUser();
		
		Animal currentAnimal = animalesRepo.findAnimalById(animal.getId());
		
		if (currentAnimal.getOwner().getId() == usuario.getId() || currentAnimal.getEstado() == Estado.RESERVADO) {
			currentAnimal.setOwner(null);
			currentAnimal.setEstado(Estado.EN_ADOPCION);
			animalesRepo.save(currentAnimal);
			redirectAttributes.addFlashAttribute("message","Has cancelado la reserva de "+currentAnimal.getNombre()).addFlashAttribute("resul","success");
		}else {
			redirectAttributes.addFlashAttribute("message","Se ha producido un error.");
		}
		
		return new RedirectView("/usuarios/reservas"); 
	}
	
	@GetMapping("/manageAnimal")
	public String pagBaja(Model model) {
		

		List<Animal> listaAnimales = animalesRepo.findAllAnimalesByEstado(Estado.RESERVADO);
		model.addAttribute("animales", listaAnimales);


		Estado[] opcionesEstado = Estado.values();
		model.addAttribute("estados", opcionesEstado);


		return "animales/manageAnimal";
	}
	
	@PostMapping("/manageAnimal-submit")
	public RedirectView cambiarEstadoAnimal(Animal animal,@RequestParam(name = "id", required = false) int idAnimal, 
			@RequestParam(name = "selectEstado", required = false) Estado estado ,Model model, RedirectAttributes redirectAttributes) {
		

    Animal currentAnimal = animalesRepo.findAnimalById(animal.getId());
	
		if (currentAnimal.getEstado()==Estado.RESERVADO && estado == Estado.ADOPTADO) { 
			currentAnimal.setOwner(currentAnimal.getOwner());
			currentAnimal.setEstado(Estado.ADOPTADO);
			animalesRepo.save(currentAnimal);
			redirectAttributes.addFlashAttribute("message","Has concedido la adopción de "+currentAnimal.getNombre()+ " a "+currentAnimal.getOwner().getNombre()+" "+currentAnimal.getOwner().getApellidos()).addFlashAttribute("resul","success");
			
		 }else if (currentAnimal.getEstado() == Estado.RESERVADO && estado == Estado.EN_ADOPCION) {
			 redirectAttributes.addFlashAttribute("message","Has rechazado la adopción de "+currentAnimal.getNombre()+ " a "+currentAnimal.getOwner().getNombre()+ " "+currentAnimal.getOwner().getApellidos()).addFlashAttribute("resul","warning");
			 currentAnimal.setOwner(null);
			 currentAnimal.setEstado(Estado.EN_ADOPCION);
			 animalesRepo.save(currentAnimal);
			  				
			}else {
				redirectAttributes.addFlashAttribute("message","Se ha producido un error.").addFlashAttribute("resul","danger");
			}
		
		return new RedirectView("/animales/manageAnimal"); 
	}
	

}
