package com.project.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {

	Rol findByRol(String rol);
	
}