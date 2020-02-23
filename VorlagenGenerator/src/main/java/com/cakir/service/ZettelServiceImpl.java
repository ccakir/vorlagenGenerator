package com.cakir.service;

import java.sql.Connection;
import java.sql.Date;
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
import com.cakir.model.Teil;
import com.cakir.model.Zettel;

public class ZettelServiceImpl implements ZettelService{
	
	private Connection conn;
	
	DatabaseConnection connect = new DatabaseConnection();
	
	private static final Log log = LogFactory.getLog(ZettelServiceImpl.class);
	
	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();
	TeilServiceImpl teilService = new TeilServiceImpl();

	@Override
	public void speichernZettel(Zettel zettel) {
		
		try {
			conn = DriverManager.getConnection(connect.connectURL, connect.user, connect.password);
			Statement stmt = conn.createStatement();
			
			int result = stmt.executeUpdate("INSERT INTO zettel(datum, grund, bemerkung, schicht, abteilung, maschiene, quantity,"
					+ "teil_id, mitarbeiter_id, type) VALUES ('"+zettel.getDatum()+"', '"+zettel.getGrund()+"',"
							+ "'"+zettel.getBemerkung()+"', '"+zettel.getSchicht()+"', '"+zettel.getAbteilung()+"', '"+zettel.getMaschiene()+"',"
									+ "'"+zettel.getQuantity()+"', '"+zettel.getTeil().getId()+"', '"+zettel.getMitarbeiter().getId()+"', '"+zettel.getType()+"')");
			
			if(result == 1) {
				log.info("Zettel : "+ zettel.getGrund() + "wurde erfolgreich gespeichert.");
				
			} else {
				log.error("Zettel konnte nicht gespeichert werden.");
				
			}
		} catch (SQLException e) {
			log.error("Zettel konnte nicht gespeichert werden.", e);
			e.printStackTrace();
			
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
	public List<Zettel> alleZettel(String type) {
		
		List<Zettel> listZettel = new ArrayList<Zettel>();
		
		try {
			conn = DriverManager.getConnection(connect.connectURL, connect.user, connect.password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM zettel WHERE type='"+type+"'ORDER BY id DESC");
			
			while(rs.next()) {
				
				Teil teil = teilService.findTeilById(rs.getLong("teil_id"));
				Mitarbeiter mitarbeiter = mitarbeiterService.findMitarbeiterById(rs.getLong("mitarbeiter_id"));
				
				
				Zettel zettel = new Zettel();
				zettel.setId(rs.getLong("id"));
				zettel.setDatum(rs.getString("datum"));
				zettel.setGrund(rs.getString("grund"));
				zettel.setBemerkung(rs.getString("bemerkung"));
				zettel.setSchicht(rs.getString("schicht"));
				zettel.setAbteilung(rs.getString("abteilung"));
				zettel.setMaschiene(rs.getString("maschiene"));
				zettel.setQuantity(rs.getInt("quantity"));
				zettel.setTeil(teil);
				zettel.setMitarbeiter(mitarbeiter);
				zettel.setType(rs.getString("type"));
				
				
				listZettel.add(zettel);
				
			}
		
			
			return listZettel;
			
		} catch (SQLException e) {
			log.error("Zettel List konnte nicht geladen werden", e);
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
	public boolean deleteZettel(long id) {
		if(zettelKontrolle(id) ) {
			
			try {
				conn = DriverManager.getConnection(connect.connectURL, connect.user, connect.password);
				Statement stmt = conn.createStatement();
				
				int result = stmt.executeUpdate("DELETE FROM zettel WHERE id='"+id+"'");
				
				if(result == 1) return true;
				else return false;
				
				
			} catch (SQLException e) {
				log.error("ID : "+id+" Zettel konnte nicht gelöscht werden.", e);
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
	public Zettel findZettelById(long id) {
		try {
			conn = DriverManager.getConnection(connect.connectURL, connect.user, connect.password);
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM zettel WHERE id='"+id+"'");
			
			if(rs.next()) {
				log.info("ID : "+id+" Zettel wurde geladen.");
				
				Teil teil = teilService.findTeilById(rs.getLong("teil_id"));
				Mitarbeiter mitarbeiter = mitarbeiterService.findMitarbeiterById(rs.getLong("mitarbeiter_id"));
				
				Zettel zettel = new Zettel();
				zettel.setId(rs.getLong("id"));
				zettel.setDatum(rs.getString("datum"));
				zettel.setGrund(rs.getString("grund"));
				zettel.setBemerkung(rs.getString("bemerkung"));
				zettel.setSchicht(rs.getString("schicht"));
				zettel.setAbteilung(rs.getString("abteilung"));
				zettel.setMaschiene(rs.getString("maschiene"));
				zettel.setQuantity(rs.getInt("quantity"));
				zettel.setTeil(teil);
				zettel.setMitarbeiter(mitarbeiter);
				zettel.setType(rs.getString("type"));
				
				return zettel;
			}
		} catch (SQLException e) {
			log.error("ID : "+id+ " Zettel konnte nicht geladen weden", e);
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
		return null;
		
		
		
	}

	@Override
	public boolean zettelKontrolle(long id) {

		if(findZettelById(id) != null) return true;
		else return false;
	}

	@Override
	public boolean updateZettel(Zettel zettel) {
		
		
		if(zettelKontrolle(zettel.getId())) {
			
			try {
				conn = DriverManager.getConnection(connect.connectURL, connect.user, connect.password);
				PreparedStatement prStatement = conn.prepareStatement("UPDATE zettel SET datum=?, grund=?, bemerkung=?, schicht=?, abteilung=?, maschiene=?, quantity=?, teil_id=?, mitarbeiter_id=?, type=? WHERE id=?");
				prStatement.setString(1, zettel.getDatum());
				prStatement.setString(2, zettel.getGrund());
				prStatement.setString(3, zettel.getBemerkung());
				prStatement.setString(4, zettel.getSchicht());
				prStatement.setString(5, zettel.getAbteilung());
				prStatement.setString(6, zettel.getMaschiene());
				prStatement.setInt(7, zettel.getQuantity());
				prStatement.setLong(8, zettel.getTeil().getId());
				prStatement.setLong(9, zettel.getMitarbeiter().getId());
				prStatement.setString(10, zettel.getType());
				prStatement.setLong(11, zettel.getId());
				
				prStatement.executeUpdate();
				
				return true;
				
			} catch (SQLException e) {
				log.error("ID : "+zettel.getId()+" Zettel Fehler beim update.", e);
				e.printStackTrace();
				return false;
			}
			
			
		} else {
			return false;
		}
	}

}
