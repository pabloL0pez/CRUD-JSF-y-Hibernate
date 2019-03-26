package model;

import java.util.HashMap;

public class QueryBuilder {

	public static String select(String tableName) {
		return "SELECT * FROM " + tableName + ";"; 
	}
	
	public static String filterSelect(String tableName, Filtro filtroCondicion) {
		String query = "SELECT * FROM " + tableName;
		String condicion = construirCondicion(filtroCondicion);
		
		if(condicion.equals(" WHERE")) {
			query = query + ";";
		} else {
			query = query + condicion + ";";
		}
		
		System.out.println(query);
		return query;
	}
	
	public static String insert(String tableName, Videojuego juego) {
		return "INSERT INTO " + tableName + " VALUES(" + juego.getClave() + ",\'" + juego.getNombre() +
				"\',\'" + juego.getGenero() + "\',\'" + juego.getPlataforma() + "\'," + juego.getPrecio() + ");";
	}
	
	public static String delete(String tableName, int clave) {
		return "DELETE FROM " + tableName + " WHERE clave=" + clave + ";";
	}
	
	public static String update(String tableName, Videojuego juego) {
		return "UPDATE " + tableName + " SET nombre=\'" + juego.getNombre() + "\',genero=\'" + juego.getGenero() + 
				"\',plataforma=\'" + juego.getPlataforma() + "\',precio=" + juego.getPrecio() + " WHERE clave=" +
				juego.getClave() + ";";
	}
	
	private static String construirCondicion(Filtro filtro) {
		String condicion = " WHERE";
		char[] condicionArray;
		int index;
		
		HashMap<String, String> rangos = new HashMap<String, String>();
		rangos.put("exactly", "=");
		rangos.put("lower_than", "<=");
		rangos.put("higher_than", ">=");
		
		if (filtro.getClave() != 0) {
			condicion = condicion + " AND clave=" + filtro.getClave();
		}
		
		if (filtro.getNombre() != null && !filtro.getNombre().equals("")) {
			condicion = condicion + " AND nombre=\'" + filtro.getNombre() + "\'";
		}
		
		if (filtro.getGenero() != null && !filtro.getGenero().equals("")) {
			condicion = condicion + " AND genero=\'" + filtro.getGenero() + "\'";
		}
		
		if (filtro.getPlataforma() != null && !filtro.getPlataforma().equals("")) {
			condicion = condicion + " AND plataforma=\'" + filtro.getPlataforma() + "\'";
		}
		
		if (filtro.getPrecio() != 0.0) {
			condicion = condicion + " AND precio" + rangos.get(filtro.getRango()) + filtro.getPrecio();
		}
		
		if(!condicion.equals(" WHERE")) {
			condicionArray = condicion.toCharArray();
			index = condicion.indexOf("AND");
			for(int i = index; i < index + 4 ; i++) {
				condicionArray[i] = ' ';
			}
			condicion = new String(condicionArray);
		}

		return condicion;
	}
	
	public static String pruebaConstruirCondicion(Filtro filtro) {
		return construirCondicion(filtro);
	}
}
