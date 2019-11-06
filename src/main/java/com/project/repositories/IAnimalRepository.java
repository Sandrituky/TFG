package com.project.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.model.Animal;

@Repository
public interface IAnimalRepository extends JpaRepository<Animal, Integer> {
	
	//_______LISTAR PERROS BY ESTADO_______________
	
	@Query(
	value="SELECT * FROM ANIMAL WHERE ESTADO = 'EN_ADOPCION' AND TIPO = 'PERRO'", nativeQuery = true)
	List <Animal> findPerritosEnAdopcion();
	
	@Query(
			value="SELECT * FROM ANIMAL WHERE ESTADO = 'RESERVADO' AND TIPO = 'PERRO'", nativeQuery = true)
			List <Animal> findPerritosReservados();
	
	@Query(
			value="SELECT * FROM ANIMAL WHERE ESTADO = 'ADOPTADO' AND TIPO = 'PERRO'", nativeQuery = true)
			List <Animal> findPerritosAdoptados();
	
	//____________LISTAR GATOS BY ESTADO_______________
	
	@Query(
	value="SELECT * FROM ANIMAL WHERE ESTADO = 'EN_ADOPCION' AND TIPO = 'GATO'", nativeQuery = true)
	List <Animal> findGatitosEnAdopcion();
	
	@Query(
			value="SELECT * FROM ANIMAL WHERE ESTADO = 'RESERVADO' AND TIPO = 'GATO'", nativeQuery = true)
			List <Animal> findGatitosReservados();
	
	@Query(
			value="SELECT * FROM ANIMAL WHERE ESTADO = 'ADOPTADO' AND TIPO = 'GATO'", nativeQuery = true)
			List <Animal> findGatitosAdoptados();
	
	
	
	//_____________BUSCAR TODOS LOS ANIMALES_____________
	
	@Query(
			value="SELECT * FROM ANIMAL", nativeQuery = true)
			List <Animal> findAllAnimalitos();
	
	//_____________BUSCAR ANIMALES POR TIPO Y SEXO__________
	
	@Query(
			value="SELECT * FROM ANIMAL WHERE TIPO = 'PERRO' AND SEXO = 'MACHO'", nativeQuery = true)
			List <Animal> findPerritosMacho();
	
	@Query(
			value="SELECT * FROM ANIMAL WHERE TIPO = 'PERRO' AND SEXO = 'HEMBRA'", nativeQuery = true)
			List <Animal> findPerritosHembra();
	
	@Query(
			value="SELECT * FROM ANIMAL WHERE TIPO = 'GATO' AND SEXO = 'MACHO'", nativeQuery = true)
			List <Animal> findGatitosMacho();
	
	@Query(
			value="SELECT * FROM ANIMAL WHERE TIPO = 'GATO' AND SEXO = 'HEMBRA'", nativeQuery = true)
			List <Animal> findGatitosHembra();
	
	//_____________BUSCAR ANIMALES POR TIPO__________
	
	@Query(
			value="SELECT * FROM ANIMAL WHERE TIPO = 'PERRO'", nativeQuery = true)
			List <Animal> findAllPerritos();
	
	@Query(
			value="SELECT * FROM ANIMAL WHERE TIPO = 'GATO'", nativeQuery = true)
			List <Animal> findAllGatitos();
	
	//_____________BUSCAR ANIMALES POR SEXO__________
	
	@Query(
			value="SELECT * FROM ANIMAL WHERE SEXO = 'MACHO'", nativeQuery = true)
			List <Animal> findAllAnimalitosMacho();
	
	@Query(
			value="SELECT * FROM ANIMAL WHERE SEXO = 'HEMBRA'", nativeQuery = true)
			List <Animal> findAllAnimalitosHembra();
	
	//____________________________
	
	
	
	
	
	
}