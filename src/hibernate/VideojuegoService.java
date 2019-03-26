package hibernate;

import java.util.ArrayList;

import model.Videojuego;

public interface VideojuegoService {

	public int addVideojuego(Videojuego juego);
	
	public int updateVideojuego(Videojuego juego);
	
	public int deleteVideojuego(int clave);
	
	public ArrayList<Videojuego> filterVideojuegos(Videojuego juego, String rango);
}
