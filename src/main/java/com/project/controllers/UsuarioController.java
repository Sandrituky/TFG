package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioRepository usuariosRepo;

	@Autowired
	private IProvinciaRepository provinciasRepo;

	@GetMapping("/altaUsuario") //
	public String pageAdd(Model model) {

		Usuario user = new Usuario();
		List<Provincia> listaProvincias = provinciasRepo.findAll();

		model.addAttribute("usuario", user);
		model.addAttribute("provincias", listaProvincias);

		return "usuarios/altaUsuario";
	}

	@PostMapping("/altaUsuario-submit") // Lo que ocurre cuando pulsas el botón de guardar, viene del form
	public RedirectView pageAddSubmit(Usuario user, Model model, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		// RedirectView redirecciona a la pagina que le digas
		
		try {
		
		if ((user.checkDNI(user.getDni())==true)&&(user.checkTelefono(user.getTelefono()) == true)&&(user.checkCP(user.getCp()) == true)
				&&(user.checkFnac(user.getFnac()) == true) && (user.checkMayorEdad(user.getFnac())==true)) {
			
			String enPass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(enPass);
			usuariosRepo.save(user);
			
			redirectAttributes.addFlashAttribute("message", "¡Te has registrado con éxito!");
		}else if(user.checkTelefono(user.getTelefono()) == false){
			redirectAttributes.addFlashAttribute("message","Teléfono inválido");
		}else if(user.checkDNI(user.getDni())==false) {
			redirectAttributes.addFlashAttribute("message","DNI inválido");
		}else if(user.checkCP(user.getCp()) == false) {
			redirectAttributes.addFlashAttribute("message","Código postal inválido");
		}else if(user.checkFnac(user.getFnac()) == false) {
			redirectAttributes.addFlashAttribute("message","Fecha de nacimiento inválida");
		}else if (user.checkMayorEdad(user.getFnac())==false) {
			redirectAttributes.addFlashAttribute("message","Debes ser mayor de edad para poder registrarte y adoptar");
		}
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("message",
						"Se produjo un error al registrarse. Por favor, inténtelo de nuevo.");
			}
		
		
		
		
		return new RedirectView("altaUsuario");
		
	}

	// return new RedirectView("altaAnimal");

}


