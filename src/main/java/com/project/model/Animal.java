package com.project.model;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;
import java.time.Period;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;

import com.project.model.Estado;
import com.project.model.Esterilizado;
import com.project.model.Tipo;
import com.project.model.Provincia;
import com.project.model.Usuario;
import com.vdurmont.emoji.EmojiParser;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;

@Table(name = "ANIMAL")
@Entity
public class Animal {

	// ATRIBUTOS

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO", nullable = false)
	private Tipo tipo;

	@Column(name = "NOMBRE", length = 50, nullable = false)
	private String nombre;

	@Enumerated(EnumType.STRING)
	@Column(name = "SEXO", nullable = false)
	private Sexo sexo;

	@Column(name = "RAZA", length = 100, nullable = false)
	private String raza;

	@Column(name = "FNAC")
	private LocalDate fnac;

	// CLAVE FORANEA A TABLA PROVINCIA, 1-N
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "PROVINCIA_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_animal_provincia"))
	private Provincia provincia;

	@Column(name = "POBLACION", length = 50, nullable = false)
	private String poblacion;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTERILIZADO", nullable = false)
	private Esterilizado esterilizado;

	@Column(name = "FOTO", length = 200, nullable = false)
	private String foto;

	@Column(name = "DESCRIPCION", length = 255, nullable = false)
	private String descripcion;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO", nullable = false)
	private Estado estado;

	// CLAVE FORANEA A TABLA USUARIO (EN CASO DE ANIMAL ADOPTADO)
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "USUARIO_ID", nullable = true, foreignKey = @ForeignKey(name = "FK_animal_usuario"))
	private Usuario owner;
	
	@Transient
	private String emojiTipo;
	
	@Transient
	private String emojiSexo;

	// CONSTRUCTOR

	public Animal() {
		super();
		this.id = 0;
		this.tipo = Tipo.NONE;
		this.nombre = "";
		this.raza = "";
		this.foto = "";
		this.esterilizado = Esterilizado.SI;
		this.descripcion = "";
		this.provincia = new Provincia();
		this.poblacion = "";
		this.fnac = LocalDate.now();
		this.sexo = Sexo.NONE;
		this.estado = Estado.EN_ADOPCION;
		this.owner = null;
		this.emojiTipo="";
		this.emojiSexo="aa";
	}

	// CONSTRUCTOR

	public Animal(int id, Tipo tipo, String nombre, String raza, String foto, Esterilizado esterilizado,
			String descripcion, Provincia provincia, String poblacion, Sexo sexo, LocalDate fnac, Estado estado,
			Usuario owner, String emojiTipo, String emojiSexo) {
		this.id = id;
		this.tipo = tipo;
		this.nombre = nombre;
		this.raza = raza;
		this.foto = foto;
		this.esterilizado = esterilizado;
		this.descripcion = descripcion;
		this.provincia = provincia;
		this.poblacion = poblacion;
		this.fnac = fnac;
		this.sexo = sexo;
		this.estado = estado;
		this.owner = owner;
		this.emojiTipo=emojiTipo;
		this.emojiSexo=emojiSexo;
		
	}

	// GETTERS & SETTERS



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

	public String getFnacSpanish() {
		String formattedDate = this.fnac.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		return formattedDate;
	}

	public String getEdad() {
		Period periodo = Period.between(fnac, LocalDate.now());
		int anios = periodo.getYears();
		int meses = periodo.getMonths();
		int dias = periodo.getDays();

		if (anios > 1) {
			if (meses == 0) {
				return anios + " años";
			} else if (meses == 1) {
				return anios + " años y " + meses + " mes";
			} else {
				return anios + " años y " + meses + " meses";
			}
		} else if (anios == 1) {
			if (meses == 0) {
				return anios + " año";
			} else if (meses == 1) {
				return anios + " año y " + meses + " mes";
			} else {
				return anios + " año y " + meses + " meses";
			}
		} else if (dias == 1) {
			return dias + " día";
		} else if (meses < 1) {
			return dias + " días";
		} else if (meses == 1) {
			return meses + " mes";
		} else if (meses < 12) {
			return meses + " meses";
		}
		return "afidfdh";
	}

	public void setFnac(String fnac) { //convierte String en LocalDate
		this.fnac = LocalDate.parse(fnac);
	}

	public String getFnac() { //Convierte LocalDate en String
		return fnac.toString();
	}
	
	public static boolean checkFnac(String fnac) { //comprueba que el animal tenga entre 0 y 40 años
		LocalDate fecha = LocalDate.parse(fnac);		
		LocalDate currentDate = LocalDate.now();
		
		int edad = Period.between(fecha, currentDate).getYears();
		
		if ((edad<41) && (fecha.isBefore(currentDate))) {  
			return true;
    }else return false;
		
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Usuario getOwner() {
		return owner;
	}

	public void setOwner(Usuario owner) {
		this.owner = owner;
	}
	
	
	@Transient
	public void setEmojiTipo(String emojiTipo) { //convierte unicode en aliases
		this.emojiTipo = EmojiParser.parseToAliases(emojiTipo);
	}
	
	
	@Transient
	public String getEmojiTipo() { //convierte aliases en unicode
		String emoji = EmojiParser.parseToUnicode(emojiTipo);
		return emoji;
	}
	
	
	//Aqui no hace falta usar EmojiParser porque son unicode muy antiguos...
	@Transient
	public void setEmojiSexo(String emojiSexo) { 
		this.emojiSexo = emojiSexo;
	}
	
	@Transient
	public String getEmojiSexo() { 
		return emojiSexo;
	}
	

	

	


	
	


}
