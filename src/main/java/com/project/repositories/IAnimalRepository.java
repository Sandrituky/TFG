package com.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Animal;
import com.project.model.Estado;
import com.project.model.Sexo;
import com.project.model.Tipo;

@Repository
public interface IAnimalRepository extends JpaRepository<Animal, Integer> {
	
	Optional <Animal> findAnimalById(int id);
	Optional <Animal> findOneAnimalById(int id);
	
	
	
	//Filtra animales por (tipo && estado), usado para mostrar las fichas de los animales
	List <Animal> findAllAnimalesByTipoAndEstado(Tipo tipo, Estado estado);
	
	
	
	//_____________________________(usado para filtrar animales en Vista Modificar)

	//Filtra animales por (tipo && sexo)
	List <Animal> findAllAnimalesByTipoAndSexo(Tipo tipo, Sexo sexo);
	
	//Filtra animales por (tipo || sexo)
	List <Animal> findAllAnimalesByTipoOrSexo(Tipo tipo, Sexo sexo);
//_______________________
	


	
	
	
	
	
}