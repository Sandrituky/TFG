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
import com.project.model.Usuario;
import com.project.model.Sexo;
import com.project.model.Estado;
import com.project.model.Esterilizado;
import com.project.repositories.IAnimalRepository;
import com.project.repositories.IProvinciaRepository;
import com.project.repositories.IUsuarioRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
		// RedirectView redirecciona a la pagina que le digas

		Boolean result = false;

		// TRATAMIENTO DE SUBIDA DE IMAGEN

		// Guardamos la extension de la imagen y comprobamos que sea alguna de las
		// especificadas
		String extensionImagen = file.getOriginalFilename().split("\\.")[1];
		extensionImagen = extensionImagen.toLowerCase();
		String[] extensionesValidas = new String[] { "jpg", "png", "jpeg", "gif", "bmp" };
		if (Arrays.asList(extensionesValidas).contains(extensionImagen)) {

			result = true;

			// Generamos un nombre random a la imagen y la guardamos junto a la extension.
			String nombreImagen = UUID.randomUUID().toString() + "." + extensionImagen;

			// Guardamos la imagen en una carpeta del proyecto para imagenes.
			String path = "C:\\Users\\svalerop\\Documents\\workspace-sts-3.9.10.RELEASE\\tfg_svp\\src\\main\\resources\\static\\imagenes\\animales\\"
					+ nombreImagen;

			// Generamos una variable de tipo archivo a partir de la ruta y nombre del nuevo
			// archivo (path), si no existe la ruta se crea.
			File dirPath = new File(path);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}

			// Transferimos la imagen subida en el formulario a la ruta previamente indicada
			// para guardarla.
			file.transferTo(dirPath);

			// Debemos asignar la variable foto del animal al nombre de la imagen subida.
			animal.setFoto(nombreImagen);

			// Y finalmente guardamos el objeto animal en la BD

			if ((animal.checkFnac(animal.getFnac()) == true) && result) {
				try {
					animalesRepo.save(animal);
					redirectAttributes.addFlashAttribute("message", "El animal se ha registrado correctamente.");
				} catch (Exception e) {
					result = false;
					redirectAttributes.addFlashAttribute("message",
							"Se produjo un error al registrarse. Por favor, inténtelo de nuevo.");
				}
			} else if (animal.checkFnac(animal.getFnac()) == false) {
				redirectAttributes.addFlashAttribute("message", "Fecha de nacimiento inválida");

			} else if (!result) {
				redirectAttributes.addFlashAttribute("message", "La extensión del archivo no es correcta");

			} else {
				redirectAttributes.addFlashAttribute("message",
						"Se produjo un error al registrar el animal. Por favor, inténtelo de nuevo.");
			}

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

		List<Animal> listaAnimales;

		if (tipo == null && sexo == null) { // no funciona
			listaAnimales = animalesRepo.findAll();
			model.addAttribute("animales", listaAnimales);
		} else if (tipo != null && sexo == null) {
			listaAnimales = animalesRepo.findAllAnimalesByTipoOrSexo(tipo, sexo);
			model.addAttribute("animales", listaAnimales);
		} else if (tipo == null && sexo != null) {
			listaAnimales = animalesRepo.findAllAnimalesByTipoOrSexo(tipo, sexo);
			model.addAttribute("animales", listaAnimales);
		} else {
			listaAnimales = animalesRepo.findAllAnimalesByTipoAndSexo(tipo, sexo);
			model.addAttribute("animales", listaAnimales);
		}

		

		return "animales/modAnimal :: animales"; //podriamos poner tambien :: #selectAnimal

	}
	
	@RequestMapping("/animalid")
	public String getAnimalByID(@RequestParam(name = "id", required = false) int idAnimal, Model model) {

		
		Esterilizado[] opcionesEsterilizado = Esterilizado.values();
		model.addAttribute("esterilizados", opcionesEsterilizado);

		List<Provincia> listaProvincias = provinciasRepo.findAll();
		model.addAttribute("provincias", listaProvincias);
		
		
		Optional <Animal> animal = animalesRepo.findOneAnimalById(idAnimal);

		
		if (animal.isPresent()) {
			model.addAttribute("selectedAnimal", animal.get());
	} else {
	    // ERROR?
	}
		
		
		return "animales/modAnimal :: selectedAnimal";
	}
	
	@PostMapping("/modAnimal-submit") // Lo que ocurre cuando pulsas el botón de guardar, viene del form
	public RedirectView updateAnimal(Animal animal, @RequestParam("file") MultipartFile file, Model model,
			RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		// RedirectView redirecciona a la pagina que le digas
		
		
		Boolean result = false;

		// TRATAMIENTO DE SUBIDA DE IMAGEN

		// Guardamos la extension de la imagen y comprobamos que sea alguna de las
		// especificadas
		String extensionImagen = file.getOriginalFilename().split("\\.")[1];
		extensionImagen = extensionImagen.toLowerCase();
		String[] extensionesValidas = new String[] { "jpg", "png", "jpeg", "gif", "bmp" };
		if (Arrays.asList(extensionesValidas).contains(extensionImagen)) {

			result = true;

			// Generamos un nombre random a la imagen y la guardamos junto a la extension.
			String nombreImagen = UUID.randomUUID().toString() + "." + extensionImagen;

			// Guardamos la imagen en una carpeta del proyecto para imagenes.
			String path = "C:\\Users\\svalerop\\Documents\\workspace-sts-3.9.10.RELEASE\\tfg_svp\\src\\main\\resources\\static\\imagenes\\animales\\"
					+ nombreImagen;

			// Generamos una variable de tipo archivo a partir de la ruta y nombre del nuevo
			// archivo (path), si no existe la ruta se crea.
			File dirPath = new File(path);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}

			// Transferimos la imagen subida en el formulario a la ruta previamente indicada
			// para guardarla.
			file.transferTo(dirPath);

			// Debemos asignar la variable foto del animal al nombre de la imagen subida.
			animal.setFoto(nombreImagen);

			// Y finalmente guardamos el objeto animal en la BD
			if ((animal.checkFnac(animal.getFnac()) == true) && result && (animalesRepo.existsById(animal.getId()))) {
				try {
					animalesRepo.save(animal);
					redirectAttributes.addFlashAttribute("message", "El animal se ha actualizado correctamente.");
				} catch (Exception e) {
					result = false;
					redirectAttributes.addFlashAttribute("message",
							"Se produjo un error al actualizar. Por favor, inténtelo de nuevo.");
				}
			} else if(!animalesRepo.existsById(animal.getId())) {
				redirectAttributes.addFlashAttribute("message", "Se ha producido un error al actualizar el animal"); //esto ocurre cuando trucas la ID desde F12
				
			}else if (animal.checkFnac(animal.getFnac()) == false) {
				redirectAttributes.addFlashAttribute("message", "Fecha de nacimiento inválida");

			} else if (!result) {
				redirectAttributes.addFlashAttribute("message", "La extensión del archivo no es correcta");

			} else {
				redirectAttributes.addFlashAttribute("message",
						"Se produjo un error al actualizar el animal. Por favor, inténtelo de nuevo.");
			}

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
