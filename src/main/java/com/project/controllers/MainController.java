package com.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class MainController {

	@GetMapping({"/","/login","/index"})
	public String redIndex(@RequestParam(defaultValue = "") String re, Model model) {
			model.addAttribute("result", re);
			        
			return "index";
		}

	

	


}