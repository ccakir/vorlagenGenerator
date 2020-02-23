package com.cakir.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DatabaseConnection {
	
	public final static String connectURL = "jdbc:mysql://localhost/vorlagengenerator?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	public  final static String user = "root";
	
	public  final static String password = "1234";
	
	public static Connection conn;

	private static final Log log = LogFactory.getLog(DatabaseConnection.class);
	
	public  boolean DatabaseConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			log.info("Treiber wurde geladen.");
			
		} catch (Exception e){
			log.error("Treiber konnte nicht geladen werden!");
			System.err.println(e);
			e.printStackTrace();
			
		}
		
		try {
			conn=DriverManager.getConnection(connectURL, user, password);
			log.info("Verbindung aufgebaut.");
			return true;
		} catch (SQLException e) {
			log.error("Keine Verbindung möglich.");
			//e.printStackTrace();
			//System.exit(-1);
		} finally {
			if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		}
		return false;
	}

}

