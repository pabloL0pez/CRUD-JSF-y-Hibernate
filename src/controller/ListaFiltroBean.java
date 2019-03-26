package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import hibernate.HibernateVideojuegoService;
import hibernate.VideojuegoService;
import model.Filtro;
import model.GestorBD;
import model.Videojuego;

@ManagedBean(name="lista_filtro")
@SessionScoped
public class ListaFiltroBean {

	private static final String PAGINA_FILTRO = "lista_con_filtro.xhtml";
	
	@ManagedProperty(value="#{filtro}")
	private FiltroBean filtro;
	private List<Videojuego> juegos = new ArrayList<Videojuego>();
	
	public ListaFiltroBean() {
		
	}
	
	public void filterVideojuegos() {
		Videojuego juego = new Videojuego(filtro.getClave(), filtro.getNombre(), filtro.getGenero(), filtro.getPlataforma(),
				filtro.getPrecio());
		String rango = filtro.getRango();
		
		VideojuegoService service = new HibernateVideojuegoService();
		juegos = service.filterVideojuegos(juego, rango);
		
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
		return (ArrayList<Videojuego>) juegos;
	}

	public void setJuegos(ArrayList<Videojuego> juegos) {
		this.juegos = juegos;
	}
}
