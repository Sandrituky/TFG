package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Id;

import com.project.model.Estado;
import com.project.model.Esterilizado;
import com.project.model.Tipo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;



@Entity
public class Animal {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "TIPO")
	private Tipo tipo;
	
	@Column(name = "NOMBRE", length = 50)
	private String nombre;
	
	@Column(name = "RAZA", length = 50)
	private String raza;
	
	@Column(name = "FOTO", length = 150)
	private String foto;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "ESTERILIZADO")
	private Esterilizado esterilizado;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "LOCALIDAD", length = 50)
	private String localidad;
	
	@Column(name = "POBLACION", length = 50)
	private String poblacion;
	
	@Column(name = "FNAC", length = 80)
	private String fnac;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SEXO")
	private Sexo sexo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO")
	private Estado estado;
	
	private int owner; // due�o del animal, en caso de estar adoptado

	public Animal(int id, Tipo tipo, String nombre, String raza, String foto, Esterilizado esterilizado, String descripcion,
			String localidad, String poblacion, Sexo sexo, String fnac, Estado estado, int owner) {
		this.id = id;
		this.tipo = tipo;
		this.nombre = nombre;
		this.raza = raza;
		this.foto = foto;
		this.esterilizado = esterilizado;
		this.descripcion = descripcion;
		this.localidad = localidad;
		this.poblacion = poblacion;
		this.fnac = fnac;
		this.sexo = sexo;
		this.estado = estado;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Esterilizado getEsterilizado() {
		return esterilizado;
	}

	public void setEsterilizado(Esterilizado esterilizado) {
		this.esterilizado = esterilizado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getFnac() {
		return fnac;
	}

	public void setFnac(String fnac) {
		this.fnac = fnac;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

}
