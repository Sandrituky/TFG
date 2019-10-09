package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.model.Animal;

@Repository
public interface IAnimalRepository extends JpaRepository<Animal, Integer> {
	
}