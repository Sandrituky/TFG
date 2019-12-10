package com.project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
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
import com.project.model.Sexo;
import com.project.model.Estado;
import com.project.model.Esterilizado;
import com.project.repositories.IAnimalRepository;
import com.project.repositories.IProvinciaRepository;
import com.project.repositories.IUsuarioRepository;
import com.project.util.FuncionesImagenes;


import java.util.List;
import java.util.Optional;

import java.io.IOException;


@Controller
@RequestMapping("/animales")
public class AnimalController {

	@Autowired
	private IAnimalRepository animalesRepo;

	@Autowired
	private IProvinciaRepository provinciasRepo;

	@Autowired
	private IUsuarioRepository usuariosRepo;

	@GetMapping("/altaAnimal") // pagina de alta Animal
	public String pagAlta(Model model) {

		Animal animal = new Animal();
		model.addAttribute("animal", animal);

		Esterilizado[] opcionesEsterilizado = Esterilizado.values();
		model.addAttribute("esterilizados", opcionesEsterilizado);

		List<Provincia> listaProvincias = provinciasRepo.findAll();
		model.addAttribute("provincias", listaProvincias);

		// No usados porque al final se decidió sacar los valores manualmente

		// Sexo[] opcionesSexo = Sexo.values();
		// model.addAttribute("sexos", Sexo.class);

		// Tipo[] opcionesTipo = Tipo.values();
		// model.addAttribute("tipos", Tipo.class);

		// si fuera un select, en vez de ENUM se pondría de esta forma:
		// model.addAttribute("tipos", opcionesTipo);

		return "animales/altaAnimal";
	}

	@PostMapping("/altaAnimal-submit") // Lo que ocurre cuando pulsas el botón de guardar, viene del form
	public RedirectView pageAddSubmit(Animal animal, @RequestParam("file") MultipartFile file, Model model,
			RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {

		if(FuncionesImagenes.isValidImage(file) && FuncionesImagenes.hasRightSize(file)) {
			animal.setFoto(file);
			if (((animal.checkFnac(animal.getFnac()) == true))) {
				try {

					animalesRepo.save(animal);
					redirectAttributes.addFlashAttribute("message", "El animal se ha registrado correctamente.").addFlashAttribute("result","success");
				} catch (Exception e) {
					redirectAttributes.addFlashAttribute("message",
							"Se produjo un error al registrarse. Por favor, inténtelo de nuevo.").addFlashAttribute("result","danger");
				}
			} else if (animal.checkFnac(animal.getFnac()) == false) {
				redirectAttributes.addFlashAttribute("message", "Fecha de nacimiento inválida").addFlashAttribute("result","danger");

			} else {
				redirectAttributes.addFlashAttribute("message",
						"Se produjo un error al registrar el animal. Por favor, inténtelo de nuevo.").addFlashAttribute("result","danger");
			}

		}else if(!FuncionesImagenes.hasRightSize(file)) {
			redirectAttributes.addFlashAttribute("message","La imagen excede el limite permitido (8MB).").addFlashAttribute("result","danger");;
		}else {
			redirectAttributes.addFlashAttribute("message","Se produjo un problema con la imagen").addFlashAttribute("result","danger");;
		}

		return new RedirectView("altaAnimal");

	}



	@GetMapping("/modAnimal") // pagina de modificacion de Animal
	public String pagMod(Model model) {

		return "animales/modAnimal";
	}

	@GetMapping("/checktiposexo")
	public String filtroTipoSexo(Model model, @RequestParam(name = "selectTipo", required = false) Tipo tipo,
			@RequestParam(name = "selectSexo", required = false) Sexo sexo) {

		List<Animal> listaAnimales = animalesRepo.findAllAnimalesByEstado(Estado.EN_ADOPCION);

		if (tipo == null && sexo == null) { // no funciona
			listaAnimales = animalesRepo.findAllAnimalesByEstado(Estado.EN_ADOPCION);
			model.addAttribute("animales", listaAnimales);
		} else if (tipo != null && sexo == null) {
			listaAnimales = animalesRepo.findAllAnimalesByEstadoAndTipoOrEstadoAndSexo(Estado.EN_ADOPCION, tipo, Estado.EN_ADOPCION, sexo);
			model.addAttribute("animales", listaAnimales);
		} else if (tipo == null && sexo != null) {
			listaAnimales = animalesRepo.findAllAnimalesByEstadoAndTipoOrEstadoAndSexo(Estado.EN_ADOPCION, tipo, Estado.EN_ADOPCION, sexo);
			model.addAttribute("animales", listaAnimales);
		} else { 
			listaAnimales = animalesRepo.findAllAnimalesByEstadoAndTipoAndSexo(Estado.EN_ADOPCION, tipo, sexo);
			model.addAttribute("animales", listaAnimales);
		}

		

		return "animales/modAnimal :: animales"; //podriamos poner tambien :: #selectAnimal

	}
	
	@RequestMapping("/animalid")
	public String getAnimalByID(@RequestParam(name = "id", required = false) int idAnimal, Model model, RedirectAttributes redirectAttributes) {

		
		Esterilizado[] opcionesEsterilizado = Esterilizado.values();
		model.addAttribute("esterilizados", opcionesEsterilizado);

		List<Provincia> listaProvincias = provinciasRepo.findAll();
		model.addAttribute("provincias", listaProvincias);
		
		
		Optional <Animal> animal = animalesRepo.findOneAnimalById(idAnimal);

		
		if (animal.isPresent()) {
			model.addAttribute("selectedAnimal", animal.get());
	} else { 
		redirectAttributes.addFlashAttribute("message","No se pudo encontrar el animal").addFlashAttribute("result","danger"); //Esto nunca debería pasar en condiciones normales
	}
		
		
		return "animales/modAnimal :: selectedAnimal";
	}
	
	@PostMapping("/modAnimal-submit") // Lo que ocurre cuando pulsas el botón de guardar, viene del form
	public RedirectView updateAnimal(Animal animal, @RequestParam("file") MultipartFile file, Model model,
			RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		
			if (((animal.checkFnac(animal.getFnac()) == true))) {
				
				if(FuncionesImagenes.isValidImage(file) && FuncionesImagenes.hasRightSize(file)) {
					animal.setFoto(file);	
					animalesRepo.save(animal);
					redirectAttributes.addFlashAttribute("message", "El animal se ha actualizado correctamente.").addFlashAttribute("result","success");
				}else if(!FuncionesImagenes.isValidImage(file)) {
					redirectAttributes.addFlashAttribute("message","El archivo no es una imagen").addFlashAttribute("result","danger");
				}else if(!FuncionesImagenes.hasRightSize(file)) {
					redirectAttributes.addFlashAttribute("message","La imagen excede el limite permitido (8MB).").addFlashAttribute("result","danger");
				}else if(file.getSize()==0) {
					animalesRepo.save(animal);
					redirectAttributes.addFlashAttribute("message", "El animal se ha actualizado correctamente.").addFlashAttribute("result","success");
				}else {
					redirectAttributes.addFlashAttribute("message","Parece que el archivo no es realmente una imagen.").addFlashAttribute("result","danger");
				}
			} else if (animal.checkFnac(animal.getFnac()) == false) {
				redirectAttributes.addFlashAttribute("message", "Fecha de nacimiento inválida").addFlashAttribute("result","danger");

			} else {
				redirectAttributes.addFlashAttribute("message",
						"Se produjo un error al registrar el animal. Por favor, inténtelo de nuevo.").addFlashAttribute("result","danger");
			}
			

		return new RedirectView("modAnimal");

	}


	// __________LISTAR PERROS_____________

	@GetMapping("/listAdoptableDogs")
	public String listAdoptableDogs(Model model) {

		List <Animal> listaAnimales = animalesRepo.findAllAnimalesByTipoAndEstado(Tipo.PERRO, Estado.EN_ADOPCION);

		model.addAttribute("animales", listaAnimales);
		model.addAttribute("tipos", Tipo.class);
		model.addAttribute("sexos", Sexo.class);
		model.addAttribute("esterilizados", Esterilizado.class);

		return "animales/listAdoptableDogs";
	}

	@GetMapping("/listReservedDogs")
	public String listReservedDogs(Model model) {

		List <Animal> listaAnimales = animalesRepo.findAllAnimalesByTipoAndEstado(Tipo.PERRO, Estado.RESERVADO);

		model.addAttribute("animales", listaAnimales);
		model.addAttribute("tipos", Tipo.class);
		model.addAttribute("sexos", Sexo.class);
		model.addAttribute("esterilizados", Esterilizado.class);

		return "animales/listReservedDogs";
	}

	@GetMapping("/listAdoptedDogs")
	public String listAdoptedDogs(Model model) {

		List <Animal> listaAnimales = animalesRepo.findAllAnimalesByTipoAndEstado(Tipo.PERRO, Estado.ADOPTADO);

		model.addAttribute("animales", listaAnimales);
		model.addAttribute("tipos", Tipo.class);
		model.addAttribute("sexos", Sexo.class);
		model.addAttribute("esterilizados", Esterilizado.class);

		return "animales/listAdoptedDogs";
	}

//__________LISTAR GATOS_________________

	@GetMapping("/listAdoptableCats")
	public String listAdoptableCats(Model model) {

		List <Animal> listaAnimales = animalesRepo.findAllAnimalesByTipoAndEstado(Tipo.GATO, Estado.EN_ADOPCION);

		model.addAttribute("animales", listaAnimales);
		model.addAttribute("tipos", Tipo.class);
		model.addAttribute("sexos", Sexo.class);
		model.addAttribute("esterilizados", Esterilizado.class);

		return "animales/listAdoptableCats";
	}

	@GetMapping("/listReservedCats")
	public String listReservedCats(Model model) {

		List <Animal> listaAnimales = animalesRepo.findAllAnimalesByTipoAndEstado(Tipo.GATO, Estado.RESERVADO);

		model.addAttribute("animales", listaAnimales);
		model.addAttribute("tipos", Tipo.class);
		model.addAttribute("sexos", Sexo.class);
		model.addAttribute("esterilizados", Esterilizado.class);

		return "animales/listReservedCats";
	}

	@GetMapping("/listAdoptedCats")
	public String listAdoptedCats(Model model) {

		List <Animal> listaAnimales = animalesRepo.findAllAnimalesByTipoAndEstado(Tipo.GATO, Estado.ADOPTADO);

		model.addAttribute("animales", listaAnimales);
		model.addAttribute("tipos", Tipo.class);
		model.addAttribute("sexos", Sexo.class);
		model.addAttribute("esterilizados", Esterilizado.class);

		return "animales/listAdoptedCats";
	}

	// ________________________________________-
	
	

}
