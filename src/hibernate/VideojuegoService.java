package hibernate;

import model.Videojuego;

public interface VideojuegoService {

	public int addVideojuego(Videojuego juego);
	
	public int updateVideojuego(Videojuego juego);
	
	public int deleteVideojuego(Videojuego juego);
}
