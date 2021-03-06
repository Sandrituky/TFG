package com.project.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.project.model.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	public Optional<Usuario> findByEmail(String email);

	

	boolean existsByEmail(String email);
	boolean existsByDni(String dni);
	boolean existsByTelefono(String telefono);
	

	
	
}


