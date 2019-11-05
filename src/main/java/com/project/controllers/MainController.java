package com.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

@Controller
public class MainController {

	@GetMapping({"/"})
	public String redIndex(Model model) {
		return "index";
	}

//	@GetMapping("altaAnimal.html")
//	public String redAlta(Model alta) {
//		return "animales/altaAnimal";
//	}
	

	


}