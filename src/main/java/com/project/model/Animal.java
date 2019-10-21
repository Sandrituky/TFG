package com.project.model;
import java.text.ParseException;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.project.model.Estado;
import com.project.model.Esterilizado;
import com.project.model.Tipo;
import com.project.model.Provincia;
import com.project.model.Usuario;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;





@Table(name="ANIMAL")
@Entity
public class Animal {
	
	//ATRIBUTOS
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "TIPO", nullable = false)
	private Tipo tipo;
	
	@Column(name = "NOMBRE", length = 50, nullable = false)
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SEXO", nullable = false)
	private Sexo sexo;
	
	@Column(name = "RAZA", length = 100, nullable = false)
	private String raza;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FNAC")
	private Date fnac;

    //CLAVE FORANEA A TABLA PROVINCIA, 1-N
	@ManyToOne(cascade=CascadeType.MERGE) @JoinColumn(name="PROVINCIA_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_animal_provincia"))
    private Provincia provincia;
	
	@Column(name = "POBLACION", length = 50, nullable = false)
	private String poblacion;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "ESTERILIZADO", nullable = false)
	private Esterilizado esterilizado;
	
	@Column(name = "FOTO", length = 200, nullable = false)
	private String foto;
	
	@Column(name = "DESCRIPCION", length = 2000, nullable = false)
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO", nullable = false)
	private Estado estado;
	
	//CLAVE FORANEA A TABLA USUARIO  (EN CASO DE ANIMAL ADOPTADO)
	@ManyToOne(cascade=CascadeType.MERGE) @JoinColumn(name="USUARIO_ID", nullable = true, foreignKey = @ForeignKey(name = "FK_animal_usuario"))
    private Usuario owner;
	
	//CONSTRUCTOR
	
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
		this.fnac = new Date();
		this.sexo = Sexo.NONE;
		this.estado = Estado.EN_ADOPCION;
		this.owner = null;
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
	



	

}
