package com.project.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
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

	@Autowired
	private AuthUserController authController;

	@GetMapping("/checkemail")
	public String duplicatedEmail(@RequestParam("email") String email) {

		boolean isFound;

		Usuario loggedUser = authController.getAuthUser();
		if (loggedUser.getEmail().equalsIgnoreCase(email)) {
			isFound = false;
		} else {
			isFound = usuariosRepo.existsByEmail(email);
		}

		return "{\"result\": " + isFound + "}";

	}

	@GetMapping("/checkdni")
	public String duplicatedDNI(@RequestParam("dni") String dni) {

		boolean isFound = usuariosRepo.existsByDni(dni);

		return "{\"result\": " + isFound + "}";

	}

	@GetMapping("/checktelefono")
	public String duplicatedPhone(@RequestParam("telefono") String telefono) {

		boolean isFound;

		Usuario loggedUser = authController.getAuthUser();
		if (loggedUser.getTelefono().equals(telefono)) {
			isFound = false;
		} else {
			isFound = usuariosRepo.existsByTelefono(telefono);
		}

		return "{\"result\": " + isFound + "}";

	}

}