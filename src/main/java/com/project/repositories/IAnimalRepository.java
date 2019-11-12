package com.project.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.model.Animal;
import com.project.model.Sexo;
import com.project.model.Tipo;

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
	

	List <Animal> findAllAnimalesByTipoAndSexo(Tipo tipo, Sexo sexo);
	List <Animal> findAllAnimalesByTipoOrSexo(Tipo tipo, Sexo sexo);
	


	
	
	
	
	
}