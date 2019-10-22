package com.project.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.model.Animal;

@Repository
public interface IAnimalRepository extends JpaRepository<Animal, Integer> {
	
	@Query(
	value="SELECT * FROM ANIMAL WHERE ESTADO = 'EN_ADOPCION' AND TIPO = 'PERRO'", nativeQuery = true)
	List <Animal> findPerritosEnAdopcion();
	
	
	
}