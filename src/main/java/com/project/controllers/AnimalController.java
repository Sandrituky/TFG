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
import org.springframework.validation.BindingResult;

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
import java.util.Collection;
import java.util.List;

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

	@GetMapping("/listAdoptableDogs")
	public String pageList(Model model) {
		
		

		Collection<Animal> listaAnimales = animalesRepo.findPerritosEnAdopcion();
		

		model.addAttribute("animales", listaAnimales);
		model.addAttribute("tipos", Tipo.class);

		return "animales/listAdoptableDogs";
	}

	@GetMapping("/altaAnimal") // pagina de alta Animal
	public String pagAlta(Model model) {

		Animal animal = new Animal();
		model.addAttribute("animal", animal);

		Esterilizado[] opcionesEsterilizado = Esterilizado.values();
		model.addAttribute("esterilizados", opcionesEsterilizado);

		List<Provincia> listaProvincias = provinciasRepo.findAll();
		model.addAttribute("provincias", listaProvincias);

		List<Usuario> listaUsuarios = usuariosRepo.findAll();

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
		String[] extensionesValidas = new String[] { "jpg", "png", "jpeg", "gif", "bmp" };
		if (Arrays.asList(extensionesValidas).contains(extensionImagen)) {

			result = true;

			// Generamos un nombre random a la imagen y la guardamos junto a la extension.
			String nombreImagen = UUID.randomUUID().toString() + "." + extensionImagen;

			// Guardamos la imagen en una carpeta del proyecto para imagenes.
			String path = "C:\\Users\\svalerop\\Documents\\workspace-sts-3.9.10.RELEASE\\tfg_svp\\src\\main\\resources\\static\\imagenes\\"
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

			try {
				// Y finalmente guardamos el objeto animal en la BD
				animalesRepo.save(animal);
			} catch (Exception e) {
				result = false;
			}
		}
		if (!result) {
			redirectAttributes.addFlashAttribute("message",
					"Se produjo un error al registrar el animal. Por favor, inténtelo de nuevo.");
		} else {
			redirectAttributes.addFlashAttribute("message", "El animal se ha registrado correctamente.");
		}
		return new RedirectView("altaAnimal");

		// return new RedirectView("altaAnimal");

	}

	@GetMapping("/bajaAnimal")
	public String pagBaja(Model model) {
		Animal animal = new Animal();
		model.addAttribute("animal", animal);
		
		
		List<Animal> listaAnimales = animalesRepo.findAll();
		model.addAttribute("animales", listaAnimales);
		
		List<Usuario> listaUsuarios = usuariosRepo.findAll();
		model.addAttribute("usuarios", listaUsuarios);


		Estado[] opcionesEstado = Estado.values();
		model.addAttribute("estados", opcionesEstado);

		return "animales/bajaAnimal";
	}

	@GetMapping("/modAnimal")
	public String pagMod(Model model) {
		return "animales/modAnimal";
	}

	@PostMapping("/add-submit")
	public RedirectView pageAddSubmit(Animal animal, Model model) {

		animalesRepo.save(animal);

		return new RedirectView("animales/add-submit");
	}
}
