package com.project.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.model.Usuario;

@Table(name = "ROL")
@Entity
public class Rol {



	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY) es una tabla ya llena
	@Column(name = "ID")
	private int id;

	@Column(name = "ROL", length = 10, nullable = false)
	private String rol;

	// USUARIO TIENE CLAVE FORANEA DE R
	@OneToMany(mappedBy = "rol")
	private List<Usuario> usuarios;


	
	public Rol(int id, String rol, List<Usuario> usuarios) {
		super();
		this.id = id;
		this.rol = rol;
		this.usuarios = usuarios;
	}


	public Rol() {
		super();
		this.id = 0;
		this.rol = "";
		this.usuarios = Arrays.asList();
	}
	
	
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
}
