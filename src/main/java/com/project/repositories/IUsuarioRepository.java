package com.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.model.Animal;
import com.project.model.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	List <Usuario> findByEmail(String email);
	public Optional<Usuario> findByUsername(String username);
	

	boolean existsByEmail(String email);
	boolean existsByDni(String dni);
	boolean existsByTelefono(String telefono);
	

	
	
}


