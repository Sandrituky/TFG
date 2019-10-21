package com.project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
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
	@Column (name = "ROL", nullable = false)
	private Rol rol;
	
	@Column(name = "DNI", length = 9, unique=true, nullable = false)
	private String dni;
	
	@Column(name = "NOMBRE", length = 50, nullable = false)
	private String nombre;
	
	@Column(name = "APELLIDOS", length = 80, nullable = false)
	private String apellidos;
	
	@Column(name = "EMAIL", length = 50, unique=true, nullable = false)
	private String email;
	
	@Column(name = "PASSWORD", length = 20, nullable = false)
	private String password;
	
	@Column(name = "FNAC", nullable = false)
	private Date fnac;
	
    //CLAVE FORANEA A TABLA PROVINCIA, 1-N
	@ManyToOne(cascade=CascadeType.MERGE) @JoinColumn(name="PROVINCIA_ID", nullable=false, foreignKey = @ForeignKey(name = "FK_usuario_provincia"))
    private Provincia provincia;
	
	@Column(name = "POBLACION", length = 50, nullable = false)
	private String poblacion;
	
	@Column(name = "CP", length = 10, nullable = false)
	private String cp;
	
	@Column(name = "DIRECCION", length = 150, nullable = false)
	private String direccion;
	
	@Column(name = "TELEFONO", length = 9, nullable = false)
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
		this.password = "";
		this.fnac = new Date();
		this.provincia = new Provincia();
		this.poblacion = "";
		this.telefono = "";
		this.direccion="";
		this.cp="";
		this.animales = Arrays.asList();
	}
	
	public Usuario(int id, Rol rol, String dni, String nombre, String apellidos, String email, String password, Date fnac,
			Provincia provincia, String poblacion, String telefono, String direccion, String cp, List<Animal> animales) {
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
		this.direccion = direccion;
		this.cp = cp;
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFnac() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("UTC+2"));
		return format.format(this.fnac);
	}

	public void setFnac(String fnac) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("UTC+2"));
		this.fnac = format.parse(fnac);
	}
	
	public String fnacSpanish() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setTimeZone(TimeZone.getTimeZone("UTC+2"));
		return format.format(this.fnac);
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
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}
	
	
	

	public List<Animal> getAnimales() {
		return animales;
	}

	public void setAnimales(List<Animal> animales) {
		this.animales = animales;
	}
	

	
	
	




}
