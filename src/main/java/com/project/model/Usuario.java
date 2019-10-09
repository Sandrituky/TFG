package com.project.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.model.Rol;
import com.project.model.Provincia;
import com.project.model.Animal;

@Table(name="USUARIO")
@Entity
public class Usuario {

	//ATRIBUTOS
	
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
	private Date fnac;
	
    //CLAVE FORANEA A TABLA PROVINCIA, 1-N
	@ManyToOne @JoinColumn(name="id")
    private Provincia provincia;
	
	@Column(name = "POBLACION", length = 50)
	private String poblacion;
	
	@Column(name = "TELEFONO", length = 9)
	private String telefono;

	
	//ANIMAL TIENE CLAVE FORANEA DE USUARIO
	@OneToMany(mappedBy = "owner")
    private List<Animal> animales;
	
	//CONSTRUCTOR
	
	
	public Usuario() {
		super();
		this.id = 1;
		this.rol = Rol.USER;
		this.dni = "";
		this.nombre = "";
		this.apellidos = "";
		this.email = "";
		this.fnac = new Date();
		this.provincia = new Provincia();
		this.poblacion = "";
		this.telefono = "";
		this.animales = Arrays.asList();
	}
	
	public Usuario(int id, Rol rol, String dni, String nombre, String apellidos, String email, Date fnac,
			Provincia provincia, String poblacion, String telefono, List<Animal> animales) {
		super();
		this.id = id;
		this.rol = rol;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.fnac = fnac;
		this.provincia = provincia;
		this.poblacion = poblacion;
		this.telefono = telefono;
		this.animales = animales;
	}
	

	
	
	
	//GETTERS & SETTERS

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

	public Date getFnac() {
		return fnac;
	}

	public void setFnac(Date fnac) {
		this.fnac = fnac;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
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
