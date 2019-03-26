package hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.QueryBuilder;
import model.Videojuego;
import util.SessionUtil;

public class HibernateVideojuegoService implements VideojuegoService{
	
	private static final String TABLE_NAME = "videojuegos";

	@Override
	public int addVideojuego(Videojuego juego) {
		int result = 0;
		
		try (Session session = SessionUtil.getSession()) {
			Transaction trans = session.beginTransaction();
			result = addVideojuego(juego, session);
			trans.commit();
		}
		
		return result;
	}
	
	private int addVideojuego(Videojuego juego, Session session) {
		int result = 0;
		
		if (getVideojuego(juego, session) == null) {
			result = 1;
			session.save(juego);
		}
		
		return result;
	}
	
	private Videojuego getVideojuego(Videojuego juego, Session session) {
		Query<Videojuego> query = session.createQuery("from videojuegos v where v.clave=:claveJuego", Videojuego.class);
		query.setParameter("claveJuego", juego.getClave());
		return query.uniqueResult();
	}

	@Override
	public int updateVideojuego(Videojuego juego) {
		int result = 0;
		
		try (Session session = SessionUtil.getSession()) {
			Transaction trans = session.beginTransaction();
			result = updateVideojuego(juego, session);
			trans.commit();
		}
		
		return result;
	}
	
	private int updateVideojuego(Videojuego nuevoJuego, Session session) {
		int result = 0;
		Videojuego juego;
		
		if ((juego = getVideojuego(nuevoJuego, session)) != null) {
			result = 1;
			if (!nuevoJuego.getNombre().equals("")) {
				juego.setNombre(nuevoJuego.getNombre());
			}
			if (!nuevoJuego.getGenero().equals("")) {
				juego.setGenero(nuevoJuego.getGenero());
			}
			if (!nuevoJuego.getPlataforma().equals("")) {
				juego.setPlataforma(nuevoJuego.getPlataforma());
			}
			if (nuevoJuego.getPrecio() != 0.0) {
				juego.setPrecio(nuevoJuego.getPrecio());
			}
		}
		
		return result;
	}

	@Override
	public int deleteVideojuego(int clave) {
		int result = 0;
		
		try (Session session = SessionUtil.getSession()) {
			Transaction trans = session.beginTransaction();
			result = deleteVideojuego(clave, session);
			trans.commit();
		}
		
		return result;
	}
	
	private int deleteVideojuego(int clave, Session session) {
		int result = 0;
		Videojuego juego;
		
		if ((juego = getVideojuego(new Videojuego(clave), session)) != null) {
			result = 1;
			session.remove(juego);
		}
		
		return result;
	}

	@Override
	public ArrayList<Videojuego> filterVideojuegos(Videojuego juego, String rango) {
		List<Videojuego> juegos;
		
		try (Session session = SessionUtil.getSession()) {
			Transaction trans = session.beginTransaction();
			juegos = new ArrayList<Videojuego>(QueryBuilder.filterSelectHibernate(juego, rango, TABLE_NAME, session).list());
			trans.commit();
		}
		
		return (ArrayList<Videojuego>) juegos;
	}
}
