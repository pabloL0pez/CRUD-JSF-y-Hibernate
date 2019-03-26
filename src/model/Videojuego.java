package model;

import javax.persistence.*;

@Entity(name="videojuegos")
public class Videojuego {

	@Id
	@Column(name="clave")
	private int clave;
	@Column(name="nombre")
	private String nombre;
	@Column(name="genero")
	private String genero;
	@Column(name="plataforma")
	private String plataforma;
	@Column(name="precio")
	private double precio;
	
	public Videojuego() {
		
	}

	public Videojuego(int clave, String nombre, String genero, String plataforma, double precio) {
		super();
		this.clave = clave;
		this.nombre = nombre;
		this.genero = genero;
		this.plataforma = plataforma;
		this.precio = precio;
	}
	
	public Videojuego(int clave) {
		this.clave = clave;
		this.nombre = "";
		this.genero = "";
		this.plataforma = "";
		this.precio = 0.0;
	}

	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
