package com.cakir.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cakir.connect.DatabaseConnection;
import com.cakir.model.Mitarbeiter;

public class MitarbeiterServiceImpl implements MitarbeiterService {

	private static final Log log = LogFactory.getLog(MitarbeiterServiceImpl.class);

	private Connection conn;
	

	@Override
	public boolean speichernMitarbeiter(Mitarbeiter mitarbeiter) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();

			stmt.executeUpdate("INSERT INTO mitarbeiter (vorname, nachname, email, tel)" + "VALUES ('"
					+ mitarbeiter.getVorname() + "','" + mitarbeiter.getNachname() + "','" + mitarbeiter.getEmail()
					+ "','" + mitarbeiter.getTel() + "')");

			log.info("Neue Mitarbeiter in Datenbank gespeichert.");
			return true;
		} catch (SQLException e) {

			log.error("Mitarbeiter könnte nicht gespeichert werden.");
			e.printStackTrace();
			return false;
		}

		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public List<Mitarbeiter> alleMitarbeiter() {

		List<Mitarbeiter> list = new ArrayList<Mitarbeiter>();
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM mitarbeiter ORDER BY id DESC");

			while (rs.next()) {
				Mitarbeiter mitarbeiter = new Mitarbeiter();
				mitarbeiter.setVorname(rs.getString("vorname"));
				mitarbeiter.setNachname(rs.getString("nachname"));
				mitarbeiter.setEmail(rs.getString("email"));
				mitarbeiter.setTel(rs.getString("tel"));
				mitarbeiter.setId(rs.getLong("id"));

				list.add(mitarbeiter);

			}
			
			return list;
		} catch (SQLException e) {

			log.error("Alle Mitarbeiter wurden nicht gelistet.");
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean deleteMitarbeiter(long id) {

		if (mitarbeiterKontrolle(id)) {

			try {
				conn = DatabaseConnection.getMySQLConnection();
				Statement stmt = conn.createStatement();
				stmt.executeUpdate("DELETE FROM mitarbeiter WHERE id='" + id + "'");
				log.info("ID : " + id + " Mitarbeiter wurde von Datenbank gelöscht.");
				return true;
			} catch (SQLException e) {
				log.error("ID : " + id + " Mitarbeiter wurde in Datenbank nicht gelöscht", e);
				e.printStackTrace();
				return false;
			}

		} else {

			log.info("ID : " + id + " Mitarbeiter wurde nicht gefunden.");
			return false;
		}
	}

	@Override
	public Mitarbeiter findMitarbeiterById(long id) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM mitarbeiter WHERE id='" + id + "'");
			if (rs.next()) {
				Mitarbeiter mitarbeiter = new Mitarbeiter(rs.getLong("id"), rs.getString("vorname"),
						rs.getString("nachname"), rs.getString("email"), rs.getString("tel"));
				
				return mitarbeiter;
			} else {
				return null;
			}

		} catch (SQLException e) {
			log.error("ID : " + id + "im Datenbank finden ein Fehler aufgetreten.", e);
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public boolean mitarbeiterKontrolle(long id) {

		if (findMitarbeiterById(id) != null)
			return true;
		else
			return false;

	}

	@Override
	public boolean updateMitarbeiter(Mitarbeiter mitarbeiter) {

		if (mitarbeiterKontrolle(mitarbeiter.getId())) {

			try {
				conn = DatabaseConnection.getMySQLConnection();
				PreparedStatement preparedStmt = conn
						.prepareStatement("UPDATE mitarbeiter SET vorname=?, nachname=?, email=?, tel=? WHERE id=?");
				preparedStmt.setString(1, mitarbeiter.getVorname());
				preparedStmt.setString(2, mitarbeiter.getNachname());
				preparedStmt.setString(3, mitarbeiter.getEmail());
				preparedStmt.setString(4, mitarbeiter.getTel());
				preparedStmt.setLong(5, mitarbeiter.getId());

				preparedStmt.executeUpdate();

				log.info("ID :" + mitarbeiter.getId() + " Mitarbeiter wurde aktualisiert.");

				return true;
			} catch (SQLException e) {
				log.error("ID : " + mitarbeiter.getId() + "im Datenbank update ein Fehler aufgetreten.", e);
				e.printStackTrace();
				return false;
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			

		} else {

			return false;
		}
	}

}
