package com.project.model;
import java.text.ParseException;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.project.model.Estado;
import com.project.model.Esterilizado;
import com.project.model.Tipo;
import com.project.model.Provincia;
import com.project.model.Usuario;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;





@Table(name="ANIMAL")
@Entity
public class Animal {
	
	//ATRIBUTOS
	
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
	
    //CLAVE FORANEA A TABLA PROVINCIA, 1-N
	@ManyToOne @JoinColumn(name="id")
    private Provincia provincia;
	
	@Column(name = "POBLACION", length = 50)
	private String poblacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FNAC")
	private Date fnac;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SEXO")
	private Sexo sexo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO")
	private Estado estado;
	
	
	//CLAVE FORANEA A TABLA USUARIO  (EN CASO DE ANIMAL ADOPTADO)
	@ManyToOne @JoinColumn(name="id")
    private Usuario owner;
	
	//CONSTRUCTOR
	
	public Animal() {
		super();
		this.id = 0;
		this.tipo = Tipo.GATO;
		this.nombre = "";
		this.raza = "";
		this.foto = "default.jpg";
		this.esterilizado = Esterilizado.SI;
		this.descripcion = "";
		this.provincia = new Provincia();
		this.poblacion = "";
		this.fnac = new Date();
		this.sexo = Sexo.MACHO;
		this.estado = Estado.EN_ADOPCION;
		this.owner = new Usuario();
	}
	
	

	//CONSTRUCTOR
	
	public Animal(int id, Tipo tipo, String nombre, String raza, String foto, Esterilizado esterilizado, String descripcion,
			Provincia provincia, String poblacion, Sexo sexo, Date fnac, Estado estado, Usuario owner) {
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
	}
	
	//GETTERS & SETTERS

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
	
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
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

}
