package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBD {
	
	private static Connection conn;
	private static final String DATABASE = "Polibyus";
	private static final String TIME_ZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	private static final String USER = "root";
	private static final String PASSWORD = "Kronos@2";
	private static final String URL = "jdbc:mysql://localhost/" + DATABASE + TIME_ZONE;
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return conn = DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public static void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

}
