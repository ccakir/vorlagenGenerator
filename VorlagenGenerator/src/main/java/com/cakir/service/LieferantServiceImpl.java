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
import com.cakir.model.Lieferant;
import com.cakir.model.Mitarbeiter;

public class LieferantServiceImpl implements LieferantService {

	private static final Log log = LogFactory.getLog(LieferantServiceImpl.class);

	private Connection conn;

	

	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();

	@Override
	public boolean speichernLieferant(Lieferant lieferant) {

		try {
			conn = DriverManager.getConnection(DatabaseConnection.connectURL, DatabaseConnection.user, DatabaseConnection.password);
			Statement stmt = conn.createStatement();

			stmt.executeUpdate("INSERT INTO lieferant (name, tel, mitarbeiter_id) VALUES ('" + lieferant.getName() + "','"
					+ lieferant.getTel() + "', '" + lieferant.getMitarbeiter().getId() + "')");

			log.info("Lieferant : " + lieferant.getName() + " wurde gepeichert.");
			return true;
		} catch (SQLException e) {
			log.error("Lieferant könnte nicht gespeichert werden.", e);
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
	}

	@Override
	public List<Lieferant> alleLieferanten() {

		List<Lieferant> list = new ArrayList<Lieferant>();

		try {
			conn = DriverManager.getConnection(DatabaseConnection.connectURL, DatabaseConnection.user, DatabaseConnection.password);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM lieferant ORDER BY id DESC");

			while (rs.next()) {

				Mitarbeiter mitarbeiter = mitarbeiterService.findMitarbeiterById(rs.getLong("mitarbeiter_id"));

				Lieferant lieferant = new Lieferant();
				lieferant.setId(rs.getLong("id"));
				lieferant.setMitarbeiter(mitarbeiter);
				lieferant.setName(rs.getString("name"));
				lieferant.setTel(rs.getString("tel"));

				list.add(lieferant);

			}

			
			return list;

		} catch (SQLException e) {
			log.error("Lieferanten konnten nicht geladen werden.", e);
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
	public boolean deleteLieferant(long id) {

		if (lieferantKontrolle(id)) {
			try {
				conn = DriverManager.getConnection(DatabaseConnection.connectURL, DatabaseConnection.user, DatabaseConnection.password);
				Statement stmt = conn.createStatement();

				stmt.executeUpdate("DELETE FROM lieferant WHERE id='" + id + "'");
				log.info("ID : " + id + " Lieferant wurde gelöscht.");
				return true;
			} catch (SQLException e) {
				log.error("ID : " + id + " Lieferant konnte nicht gelöscht werden.", e);
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

	@Override
	public Lieferant findLieferantById(long id) {

		try {
			conn = DriverManager.getConnection(DatabaseConnection.connectURL, DatabaseConnection.user, DatabaseConnection.password);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM lieferant WHERE id='" + id + "'");

			if (rs.next()) {
				Mitarbeiter mitarbeiter = mitarbeiterService.findMitarbeiterById(rs.getLong("mitarbeiter_id"));
				Lieferant lieferant = new Lieferant(rs.getLong("id"), rs.getString("name"), rs.getString("tel"),
						mitarbeiter);

				
				return lieferant;
			} else {
				log.error("ID : " + id + " Lieferant konnte nicht gefunden werden.(Wert=null)");
				return null;
			}

		} catch (SQLException e) {
			log.error("ID : " + id + "Lieferant konnte nicht gefunden werden.", e);
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
	public boolean lieferantKontrolle(long id) {

		if (findLieferantById(id) != null) {
			
			return true;
		} else {
			log.error("ID : " + id + " Lieferant konnte nicht geladen werden.");
			return false;
		}

	}

	@Override
	public boolean updateLieferant(Lieferant lieferant) {

		if (lieferantKontrolle(lieferant.getId())) {

			try {
				conn = DriverManager.getConnection(DatabaseConnection.connectURL, DatabaseConnection.user, DatabaseConnection.password);
				PreparedStatement prStatement = conn
						.prepareStatement("UPDATE lieferant SET name=?, tel=?, mitarbeiter_id=? WHERE id=?");
				
				prStatement.setString(1, lieferant.getName());
				prStatement.setString(2, lieferant.getTel());
				prStatement.setLong(3, lieferant.getMitarbeiter().getId());
				prStatement.setLong(4, lieferant.getId());
				prStatement.executeUpdate();
				
				log.info("ID : "+lieferant.getId()+ " Lieferant wurde aktualisiert.");
				return true;

			} catch (SQLException e) {
				log.error("ID : " + lieferant.getId() + "beim Update ein Fehler ist aufgetreten", e);
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

		} else
			return false;

	}

}
