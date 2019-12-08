package com.project.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.naming.SizeLimitExceededException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.project.model.Animal;
import com.project.repositories.IAnimalRepository;


public class FuncionesImagenes {
	

	private static long maxFileSize=8192000;
	private static long maxRequestSize= 8192000;
	
	@Autowired
	private IAnimalRepository animalesRepo;

	public static boolean isValidImage(MultipartFile file) throws IOException {

		String extensionImagen = FilenameUtils.getExtension(file.getOriginalFilename());
		extensionImagen = extensionImagen.toLowerCase();
		String[] extensionesValidas = new String[] { "jpg", "png", "jpeg", "gif", "bmp" };

		if (Arrays.asList(extensionesValidas).contains(extensionImagen)) {
			

			if (ImageIO.read(file.getInputStream()) != null) {
				
				return true;
				
			} else return false;
			


		} else return false;
		

	}
	
	public static boolean hasRightSize(MultipartFile file) {
		long size=file.getSize();
		
		if(size<=maxFileSize && size<=maxRequestSize) {
			return true;
		}else return false;
			
	}
	
/*public String setPhotoName(MultipartFile file) throws IOException {
		
		//byte[] imageByte = Base64.getEncoder().encode(file.getBytes());
		String extensionImagen = FilenameUtils.getExtension(file.getOriginalFilename());
		
		Optional <Animal> animal = animalesRepo.findAnimalById(this.getId());

		
		if (animal.isPresent()) {
	
		
		String nombreImagen = UUID.randomUUID().toString() + "." + extensionImagen;
		
		
		
		
		
		return nombreImagen;
		
	}
	}
	*/


	
}





  
 
 

