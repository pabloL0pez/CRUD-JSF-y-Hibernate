package model;

public class Videojuego {

	private int clave;
	private String nombre;
	private String genero;
	private String plataforma;
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
