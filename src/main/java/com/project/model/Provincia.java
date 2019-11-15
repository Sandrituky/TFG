package com.project.model;

import java.util.Arrays;
import java.util.List;
import com.project.model.Animal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="PROVINCIA")
@Entity
public class Provincia {
	
	
	//ATRIBUTOS
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO) // es una tabla ya llena
	@Column(name = "ID")
	private int id;
	
	@Column(name = "COD", length = 2, nullable = false)
	private int cod;
	
	@Column(name = "PROVINCIA", length = 30, nullable = false)
	private String provincia;
	
	
	//ANIMAL TIENE CLAVE FORANEA DE PROVINCIA
	@OneToMany(mappedBy = "provincia")
    private List<Animal> animales;
	
	//USUARIO TIENE CLAVE FORANEA DE PROVINCIA
	@OneToMany(mappedBy = "provincia")
    private List<Usuario> usuarios;


	
	// CONSTRUCTOR
	
	public Provincia() {
		super();
		this.id = 0;
		this.cod = 1;
		this.provincia = "";
		this.animales = Arrays.asList();
		this.usuarios = Arrays.asList();
	}
	
	public Provincia(int id, int cod, String provincia, List<Animal> animales, List<Usuario> usuarios) {
		super();
		this.id = id;
		this.cod = cod;
		this.provincia = provincia;
		this.animales = animales;
		this.usuarios = usuarios;
	}
	
	//GETTERS & SETTERS	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public List<Animal> getAnimales() {
		return animales;
	}

	public void setAnimales(List<Animal> animales) {
		this.animales = animales;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
	
	



	