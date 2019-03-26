package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Filtro;
import model.GestorBD;
import model.Videojuego;

@ManagedBean(name="lista_filtro")
@SessionScoped
public class ListaFiltroBean {

	private static final String PAGINA_FILTRO = "lista_con_filtro.xhtml";
	
	@ManagedProperty(value="#{filtro}")
	private FiltroBean filtro;
	private ArrayList<Videojuego> juegos = new ArrayList<Videojuego>();
	
	public ListaFiltroBean() {
		
	}
	
	public void filterVideojuegos() {
		Filtro filtroJuegos = new Filtro(this.filtro.getClave(), this.filtro.getNombre(),
				this.filtro.getGenero(), this.filtro.getPlataforma(), this.filtro.getPrecio(), this.filtro.getRango());
		juegos = GestorBD.filterVideojuegos(filtroJuegos);
		
		filtro.resetValues(); // reinicio los valores del bean filtro al default
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(PAGINA_FILTRO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public FiltroBean getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroBean filtro) {
		this.filtro = filtro;
	}

	public ArrayList<Videojuego> getJuegos() {
		return juegos;
	}

	public void setJuegos(ArrayList<Videojuego> juegos) {
		this.juegos = juegos;
	}
}
