package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import hibernate.HibernateVideojuegoService;
import model.GestorBD;
import model.Videojuego;

@ManagedBean(name="controller")
@SessionScoped
public class ControllerBean {
	
	private static final String PAGINA_MSG = "msg.xhtml";
	
	private int clave;
	private String nombre;
	private String genero;
	private String plataforma;
	private double precio;
	
	ArrayList<Videojuego> juegos = new ArrayList<Videojuego>();
	
	private String mensaje = "Mensaje genérico";
	private String colorMensaje = "purple";

	public ControllerBean() {
		juegos = GestorBD.readVideojuegos();
	}
	
	/* Métodos CRUD */
	
	// Create HIBERNATE
	public void createVideojuego() {
		Videojuego juego = new Videojuego(clave, nombre, genero, plataforma, precio);
		HibernateVideojuegoService service = new HibernateVideojuegoService();
		
		if (service.addVideojuego(juego)!= 0) {
			setMensaje("Se ha creado el videojuego " + juego.getNombre() + "!");
			setColorMensaje("green");
		} else {
			setMensaje("No se pudo crear el videojuego");
			setColorMensaje("red");
		}
		
		refresh();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(PAGINA_MSG);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Read
	public ArrayList<Videojuego> readVideojuegos() {
		 return GestorBD.readVideojuegos();
	}
	
	// Update
	public void updateVideojuego() {
		Videojuego juego = new Videojuego(clave, nombre, genero, plataforma, precio);
		
		if (GestorBD.updateVideojuego(juego) != 0) {
			setMensaje("Se ha modificado el videojuego con clave " + juego.getClave() + "!");
			setColorMensaje("green");
		} else {
			setMensaje("No se pudo modificar el videojuego");
			setColorMensaje("red");
		}
		
		refresh();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(PAGINA_MSG);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Delete
	public void deleteVideojuego() {
		if (GestorBD.deleteVideojuego(clave) != 0) {
			setMensaje("Se ha borrado el videojuego con clave " + clave + "!");
			setColorMensaje("green");
		} else {
			setMensaje("No se pudo borrar el videojuego");
			setColorMensaje("red");
		}
		
		refresh();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(PAGINA_MSG);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public ArrayList<Videojuego> getJuegos() {
		return juegos;
	}

	public void setJuegos(ArrayList<Videojuego> juegos) {
		this.juegos = juegos;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getColorMensaje() {
		return colorMensaje;
	}

	public void setColorMensaje(String colorMensaje) {
		this.colorMensaje = colorMensaje;
	}
	
	private void refresh() {
		setClave(0);
		setNombre("");
		setGenero("");
		setPlataforma("");
		setPrecio(0.0);
		juegos = GestorBD.readVideojuegos();
	}
}
