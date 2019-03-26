package hibernate;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteVideojuego(Videojuego juego) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
