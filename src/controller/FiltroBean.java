package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="filtro")
@SessionScoped
public class FiltroBean {
	
	private int clave;
	private String nombre;
	private String genero;
	private String plataforma;
	private double precio;
	
	private String rango;

	public FiltroBean() {
		
	}
	
	public void resetValues() {
		setClave(0);
		setNombre("");
		setGenero("");
		setPlataforma("");
		setPrecio(0.0);
		setRango("exactly");
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
	
	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}
}
