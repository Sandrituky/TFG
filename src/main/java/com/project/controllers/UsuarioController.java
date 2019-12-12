package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.ui.Model;

import com.project.model.Animal;
import com.project.model.Estado;
import com.project.model.Esterilizado;
import com.project.model.Provincia;
import com.project.model.Usuario;
import com.project.model.Rol;
import com.project.model.Sexo;
import com.project.model.Tipo;
import com.project.repositories.IAnimalRepository;
import com.project.repositories.IProvinciaRepository;
import com.project.repositories.IUsuarioRepository;
import com.project.repositories.IRolRepository;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioRepository usuariosRepo;

	@Autowired
	private IProvinciaRepository provinciasRepo;
	
	@Autowired
	private IAnimalRepository animalesRepo;
	
	@Autowired
	private IRolRepository rolRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthUserController authController;

	@GetMapping("/altaUsuario") public String addUser(Model model) {

		Usuario user = new Usuario();
		List<Provincia> listaProvincias = provinciasRepo.findAll();

		model.addAttribute("usuario", user);
		model.addAttribute("provincias", listaProvincias);

		return "usuarios/altaUsuario";
	}

	@PostMapping("/altaUsuario-submit") // Lo que ocurre cuando pulsas el botón de guardar, viene del form
	public RedirectView addUserSubmit(Usuario user, Model model, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		// RedirectView redirecciona a la pagina que le digas
		
		try {
		
		if ((Usuario.checkDNI(user.getDni())==true)&&(Usuario.checkTelefono(user.getTelefono()) == true)&&(Usuario.checkCP(user.getCp()) == true)
				&&(Usuario.checkFnac(user.getFnac()) == true) && (Usuario.checkMayorEdad(user.getFnac())==true)) {
			

			Rol rol = rolRepo.findByRol("USER");			
			
			user.setRol(rol);				
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			usuariosRepo.save(user);
			
			redirectAttributes.addFlashAttribute("message", "¡Te has registrado con éxito!").addFlashAttribute("resul","success");
		}else if(Usuario.checkTelefono(user.getTelefono()) == false){
			redirectAttributes.addFlashAttribute("message","Teléfono inválido").addFlashAttribute("resul","danger");
		}else if(Usuario.checkDNI(user.getDni())==false) {
			redirectAttributes.addFlashAttribute("message","DNI inválido").addFlashAttribute("resul","danger");
		}else if(Usuario.checkCP(user.getCp()) == false) {
			redirectAttributes.addFlashAttribute("message","Código postal inválido").addFlashAttribute("resul","danger");
		}else if(Usuario.checkFnac(user.getFnac()) == false) {
			redirectAttributes.addFlashAttribute("message","Fecha de nacimiento inválida").addFlashAttribute("resul","danger");
		}else if (Usuario.checkMayorEdad(user.getFnac())==false) {
			redirectAttributes.addFlashAttribute("message","Debes ser mayor de edad para poder registrarte y adoptar").addFlashAttribute("resul","danger");
		}
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("message",
						"Se produjo un error al registrarse. Por favor, inténtelo de nuevo.").addFlashAttribute("resul","danger");
			}
		
		return new RedirectView("/index");
		
	}
	
	 @GetMapping("/login")
   public String login(Model model) {
       return "fragments/login";
   }
	
	@GetMapping("/reservas") public String pagReservasUsuarios(Model model) {
		
		Usuario usuario = authController.getAuthUser();

		List <Animal> listaAnimales = animalesRepo.findAllAnimalesByOwnerAndEstado(usuario, Estado.RESERVADO);
		
		model.addAttribute("animales", listaAnimales);	
		model.addAttribute("tipos", Tipo.class);
		model.addAttribute("sexos", Sexo.class);
		model.addAttribute("esterilizados", Esterilizado.class);

		return "usuarios/reservas";
		
		
	}
	
	@GetMapping("/modUsuario") public String modUser(Model model) {

		Usuario user = authController.getAuthUser();
		List<Provincia> listaProvincias = provinciasRepo.findAll();

		model.addAttribute("usuario", user);
		model.addAttribute("provincias", listaProvincias);

		return "usuarios/modUsuario";
	}
	
	@PostMapping("/modUsuario-submit") // Lo que ocurre cuando pulsas el botón de guardar, viene del form
	public RedirectView modUserSubmit(Usuario user, Model model, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		// RedirectView redirecciona a la pagina que le digas
		
		try {
		
		if ((Usuario.checkTelefono(user.getTelefono()) == true)&&(Usuario.checkCP(user.getCp()) == true)) {

			usuariosRepo.save(user);
			
			redirectAttributes.addFlashAttribute("message", "¡Tus datos se han actualizado con éxito!").addFlashAttribute("resul","success");
		}else if(Usuario.checkTelefono(user.getTelefono()) == false){
			redirectAttributes.addFlashAttribute("message","Teléfono inválido").addFlashAttribute("resul","danger");
		}else if(Usuario.checkCP(user.getCp()) == false) {
			redirectAttributes.addFlashAttribute("message","Código postal inválido").addFlashAttribute("resul","danger");
		}
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("message",
						"Se produjo un error al registrarse. Por favor, inténtelo de nuevo.").addFlashAttribute("resul","danger");
			}
		
		return new RedirectView("/index");
		
	}
	



}


