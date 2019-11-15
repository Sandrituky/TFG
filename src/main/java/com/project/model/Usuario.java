package com.project.model;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.model.Provincia;
import com.project.model.Animal;
import com.project.model.Rol;

@Table(name = "USUARIO")
@Entity
public class Usuario {

	// ATRIBUTOS

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;



	@Column(name = "DNI", length = 9, unique = true, nullable = false)
	private String dni;

	@Column(name = "NOMBRE", length = 50, nullable = false)
	private String nombre;

	@Column(name = "APELLIDOS", length = 80, nullable = false)
	private String apellidos;

	@Column(name = "EMAIL", length = 50, unique = true, nullable = false)
	private String email;

	@Column(name = "PASSWORD", length = 60, nullable = false)
	private String password;
	
	

	@Column(name = "FNAC", nullable = false)
	private LocalDate fnac;

	// CLAVE FORANEA A TABLA PROVINCIA, 1-N
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "PROVINCIA_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_provincia"))
	private Provincia provincia;

	@Column(name = "POBLACION", length = 50, nullable = false)
	private String poblacion;

	@Column(name = "CP", length = 10, nullable = false)
	private String cp;

	@Column(name = "DIRECCION", length = 150, nullable = false)
	private String direccion;

	@Column(name = "TELEFONO", length = 13, unique = true, nullable = false)
	private String telefono;
	
	// ANIMAL TIENE CLAVE FORANEA DE USUARIO
	@OneToMany(mappedBy = "owner")
	private List<Animal> animales;
	
  //ROL, CLAVE FORANEA A TABLA ROL, 1-N
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "ROL_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_rol"))
	private Rol rol;

	
	


	// CONSTRUCTOR


	public Usuario() {
		super();
		this.id = 0;
		this.dni = "";
		this.nombre = "";
		this.apellidos = "";
		this.email = "";
		this.password = "";
		this.fnac = LocalDate.now();
		this.provincia = new Provincia();
		this.poblacion = "";
		this.telefono = "";
		this.direccion = "";
		this.cp = "";
		this.animales = Arrays.asList();
	}

	public Usuario(int id, String dni, String nombre, String apellidos, String email, String password,
			LocalDate fnac, Provincia provincia, String poblacion, String telefono, String direccion, String cp,
			List<Animal> animales) {
		super();
		this.id = id;
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

	// GETTERS & SETTERS

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
	
	public boolean hasRol(String role) {
		boolean result = false;
		
		
			if(this.rol.getRol().equals(role)) {
				result = true;
			}
			return result;
	}


	

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public static boolean checkDNI(String dni) {

		boolean correcto = false;

		Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");

		Matcher matcher = pattern.matcher(dni);

		if (matcher.matches()) {

			String letra = matcher.group(2);

			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

			int index = Integer.parseInt(matcher.group(1));

			index = index % 23;

			String reference = letras.substring(index, index + 1);

			if (reference.equalsIgnoreCase(letra)) {

				correcto = true;

			} else {
				correcto = false;
			}
			
		} else {
			correcto = false;
		}
		return correcto;
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
		//this.username=email;
	}
	
	public static boolean checkEmail(String email) {
		 String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return email.matches(regex);
	}

	//________CONTRASEÑAS_______________________
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	//_________FECHAS________________________________

	public void setFnac(String fnac) { //Convierte String a LocalDate
		this.fnac = LocalDate.parse(fnac);
	}

	public String getFnac() { //Convierte LocalDate a String
		return fnac.toString();
	}

	public String getFnacSpanish() { //Convierte LocalDate a String con fecha en formato español
		String formattedDate = this.fnac.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		return formattedDate;
	}
	
	public static boolean checkFnac(String fnac) { //comprueba que fecha = entre el año 1900 y hoy.
		LocalDate fecha = LocalDate.parse(fnac);		
		LocalDate currentDate = LocalDate.now();
		
		if ((fecha.getYear() > 1900) && (fecha.isBefore(currentDate))) {  
			return true;
        }else return false;
		
	}
	
	public static boolean checkMayorEdad(String fnac) { //comprueba que sea mayor de edad
		LocalDate fecha = LocalDate.parse(fnac);
		LocalDate currentDate = LocalDate.now();
		
		int edad = Period.between(fecha, currentDate).getYears();
		
		if (edad>=18) {
			return true;
		}else return false;
		
	}
	
	//______________________________

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
	
	public static boolean checkTelefono(String telefono) {
		Pattern pattern = Pattern.compile("^(\\+34|0034|34)?[6|7|9][0-9]{8}$");
		return pattern.matcher(telefono).matches();
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
	
	public static boolean checkCP(String CP) {
		Pattern pattern = Pattern.compile("^(?:0[1-9]|[1-4]\\d|5[0-2])\\d{3}$");
		return pattern.matcher(CP).matches();
	}

	public List<Animal> getAnimales() {
		return animales;
	}

	public void setAnimales(List<Animal> animales) {
		this.animales = animales;
	}
	
	@Override
	public boolean equals(Object obj) {		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == 0) {
			if (other.id != 0)
				return false;
		} else if (id != other.id)
			return false;
		return true;
	}



}
