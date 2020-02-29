package com.cakir.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DatabaseConnection {
	

	
	

	/*
	 * 
	 * public final static String connectURL = "jdbc:mysql://www.casebilisim.com:3306/casebili_zettel";
	
	public  final static String user = "casebili_zettel";
	
	public  final static String password = "52mPkNEo";
	 * public final static String connectURL = "jdbc:mysql://5.250.251.4/~ccakir:3306/ccakir_zettel";
	
	public  final static String user = "ccakir_zettel";
	
	public  final static String password = "sYkzJVI6Z";
	 * 
	 * public final static String connectURL = "jdbc:mysql://www.db4free.net:3306/zettel";
	
	public  final static String user = "cak8675";
	
	public  final static String password = "Zg563266";
	 * 
	 * 
	 
	
	
	*/
	
public final static String connectURL = "jdbc:mysql://localhost/vorlagengenerator?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	public  final static String user = "root";
	
	public  final static String password = "1234";
	public static Connection conn;

	private static final Log log = LogFactory.getLog(DatabaseConnection.class);
	
	public  void verbindung() throws SQLException {
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			log.info("Treiber wurde geladen.");
			
		} catch (Exception e){
			log.error("Treiber konnte nicht geladen werden!");
			
			
			
		}
		
		try {
			conn=DriverManager.getConnection(connectURL, user, password);
			log.info("Verbindung aufgebaut.");
			
		} catch (SQLException e) {
			log.error("Keine Verbindung möglich.", e);
			JOptionPane.showMessageDialog(null, "Keine Verbindung mit Datenbank", "VERBINDUNGSFEHLER", JOptionPane.ERROR_MESSAGE);
			
		} finally {
			if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("Connection ist nicht closed!", e);
			}
			}
		}
		
	}

}

