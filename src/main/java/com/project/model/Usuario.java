package com.project.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.model.Rol;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "ROL")
	private Rol rol;
	
	@Column(name = "DNI", length = 9)
	private String dni;
	
	@Column(name = "NOMBRE", length = 50)
	private String nombre;
	
	@Column(name = "APELLIDOS", length = 50)
	private String apellidos;
	
	@Column(name = "EMAIL", length = 50)
	private String email;
	
	@Column(name = "FNAC")
	private LocalDate fnac;
	
	@Column(name = "LOCALIDAD", length = 50)
	private String localidad;
	
	@Column(name = "POBLACION", length = 50)
	private String poblacion;
	
	@Column(name = "TELEFONO", length = 9)
	private String telefono;

	public Usuario(int id, Rol rol, String dni, String nombre, String apellidos, String email, LocalDate fnac,
			String localidad, String poblacion, String telefono) {
		this.id = id;
		this.rol = rol;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.fnac = fnac;
		this.localidad = localidad;
		this.poblacion = poblacion;
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFnac() {
		return fnac;
	}

	public void setFnac(LocalDate fnac) {
		this.fnac = fnac;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
