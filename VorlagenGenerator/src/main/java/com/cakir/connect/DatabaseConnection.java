package com.cakir.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DatabaseConnection {

	private static final Log log = LogFactory.getLog(DatabaseConnection.class);

	public static Connection getMySQLConnection() throws SQLException {

		String hostName = "localhost";
		String dbName = "vorlagengenerator";
		String userName = "root";
		String password = "1234";

		return getMySQLConnection(hostName, dbName, userName, password);

	}

	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) {

		String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName
				+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connectionURL, userName, password);
		} catch (SQLException e) {
			log.error("Keine Verbindung möglich.", e);
			JOptionPane.showMessageDialog(null, "Keine Verbindung mit Datenbank", "VERBINDUNGSFEHLER",
					JOptionPane.ERROR_MESSAGE);
		}
		return conn;
	}

}

