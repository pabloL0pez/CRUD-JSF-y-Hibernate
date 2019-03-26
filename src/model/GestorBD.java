package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorBD {
	
	private static final String TABLA_VIDEOJUEGOS = "Videojuegos";
	
	public static ArrayList<Videojuego> readVideojuegos() {
		ArrayList<Videojuego> juegos = new ArrayList<Videojuego>();
		
		try {
			Connection conn = ConectarBD.getConnection();
			Statement stat = conn.createStatement();
			ResultSet qRes = stat.executeQuery(QueryBuilder.select(TABLA_VIDEOJUEGOS));
			
			while(qRes.next()) {
				juegos.add(new Videojuego(qRes.getInt("clave"), qRes.getString("nombre"),
						qRes.getString("genero"), qRes.getString("plataforma"), qRes.getDouble("precio")));
			}
			
			ConectarBD.closeConnection();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return juegos;
	}
	
	public static ArrayList<Videojuego> filterVideojuegos(Filtro filtro) {
		ArrayList<Videojuego> juegos = new ArrayList<Videojuego>();
		
		try {
			Connection conn = ConectarBD.getConnection();
			Statement stat = conn.createStatement();
			ResultSet qRes = stat.executeQuery(QueryBuilder.filterSelect(TABLA_VIDEOJUEGOS, filtro));
			
			while(qRes.next()) {
				juegos.add(new Videojuego(qRes.getInt("clave"), qRes.getString("nombre"),
						qRes.getString("genero"), qRes.getString("plataforma"), qRes.getDouble("precio")));
			}
			
			ConectarBD.closeConnection();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return juegos;
	}
	
	public static int createVideojuego(Videojuego juego) {
		int resultInsert = 0;
		
		if(juego.getClave() == 0 || juego.getNombre() == "" || juego.getGenero() == "" || juego.getPlataforma() == "" || 
				juego.getPrecio() == 0.0) {
			return resultInsert;
		}
		
		try {
			Connection conn = ConectarBD.getConnection();
			Statement stat = conn.createStatement();
			resultInsert = stat.executeUpdate(QueryBuilder.insert(TABLA_VIDEOJUEGOS, juego));
			
			ConectarBD.closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return resultInsert;
	}
	
	public static int deleteVideojuego(int clave) {
		int resultDelete = 0;
		
		try {
			Connection conn = ConectarBD.getConnection();
			Statement stat = conn.createStatement();
			resultDelete = stat.executeUpdate(QueryBuilder.delete(TABLA_VIDEOJUEGOS, clave));
			
			ConectarBD.closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return resultDelete;
	}
	
	public static int updateVideojuego(Videojuego juego) {
		int resultUpdate = 0;
		
		if(juego.getClave() == 0) {
			return resultUpdate;
		}
		
		rellenarJuego(juego); // Relleno los campos vacíos con la información que ya tenía almacenada del juego.
		
		try {
			Connection conn = ConectarBD.getConnection();
			Statement stat = conn.createStatement();
			resultUpdate = stat.executeUpdate(QueryBuilder.update(TABLA_VIDEOJUEGOS, juego));
			
			ConectarBD.closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return resultUpdate;
	}
	
	private static void rellenarJuego(Videojuego juego) {
		try {
			Connection conn = ConectarBD.getConnection();
			Statement stat = conn.createStatement();
			ResultSet qRes = stat.executeQuery(QueryBuilder.filterSelect(TABLA_VIDEOJUEGOS, new Filtro(juego.getClave(),
					"", "", "", 0.0)));
			
			if (qRes.next()) {
				System.out.println(juego.getClave());
				System.out.println(juego.getNombre());
				System.out.println(juego.getGenero());
				System.out.println(juego.getPlataforma());
				System.out.println(juego.getPrecio());
	
				if (juego.getNombre() == "" || juego.getNombre() == null) {
					juego.setNombre(qRes.getString("nombre"));
				}
				
				if (juego.getGenero() == "" || juego.getGenero() == null) {
					juego.setGenero(qRes.getString("genero"));
				}
				
				if (juego.getPlataforma() == "" || juego.getPlataforma() == null) {
					juego.setPlataforma(qRes.getString("plataforma"));
				}
				
				if (juego.getPrecio() == 0.0) {
					juego.setPrecio(qRes.getDouble("precio"));
				}
				
				System.out.println(juego.getClave());
				System.out.println(juego.getNombre());
				System.out.println(juego.getGenero());
				System.out.println(juego.getPlataforma());
				System.out.println(juego.getPrecio());
			}
			ConectarBD.closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
