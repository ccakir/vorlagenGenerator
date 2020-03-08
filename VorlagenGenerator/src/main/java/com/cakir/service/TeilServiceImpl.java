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
import com.cakir.model.Teil;

public class TeilServiceImpl implements TeilService{
	
	private static final Log log = LogFactory.getLog(TeilServiceImpl.class);
	
	private Connection conn;
	
	
	
	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();
	LieferantServiceImpl lieferantService = new LieferantServiceImpl();
	

	@Override
	public boolean speichernTeil(Teil teil) {
		
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			int result = stmt.executeUpdate("INSERT INTO teil(teilname, teilenummer, mitarbeiter_id, lieferant_id) VALUES"
					+ "('"+teil.getTeilname().toUpperCase()+"', '"+teil.getTeilenummer()+"', '"+teil.getMitarbeiter().getId()+"',"
							+ "'"+teil.getLieferant().getId()+"')");
			if(result == 1) {
				log.info("Teil : "+teil.getTeilenummer()+ " wurde gespeichert.");
			return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			log.error("Teil konnte nicht gespeichert werden.", e);
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
	public List<Teil> alleTeile() {
		
		List<Teil> list = new ArrayList<Teil>();
		
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM teil ORDER BY id DESC");
			while(rs.next()) {
				
				Mitarbeiter mitarbeiter = mitarbeiterService.findMitarbeiterById(rs.getLong("mitarbeiter_id"));
				Lieferant lieferant = lieferantService.findLieferantById(rs.getLong("lieferant_id"));
				
				Teil teil = new Teil();
				teil.setId(rs.getLong("id"));
				teil.setTeilname(rs.getString("teilname"));
				teil.setTeilenummer(rs.getString("teilenummer"));
				teil.setLieferant(lieferant);
				teil.setMitarbeiter(mitarbeiter);
				
				list.add(teil);
							
			} 
			
			
			return list;
		} catch (SQLException e) {
			log.error("Teile konnten nicht geladen werden", e);
			e.printStackTrace();
			return null;
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
	public boolean deleteTeil(long id) {

		if(teilKontrolle(id) ) {
			try {
				conn = DatabaseConnection.getMySQLConnection();
				Statement stmt = conn.createStatement();
				
				int result = stmt.executeUpdate("DELETE FROM teil WHERE id='"+id+"'");
				if(result == 1) return true;
				else return false;
			} catch (SQLException e) {
				log.info("ID : "+id+ "Teil konnte nicht gelöscht werden", e);
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
	public Teil findTeilById(long id) {


		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM teil WHERE id='"+id+"'");
			
			if(rs.next()) {
				
				Mitarbeiter mitarbeiter = mitarbeiterService.findMitarbeiterById(rs.getLong("mitarbeiter_id"));
				Lieferant lieferant = lieferantService.findLieferantById(rs.getLong("lieferant_id"));
				
				Teil teil = new Teil(rs.getLong("id"), rs.getString("teilname"),
						rs.getString("teilenummer"), mitarbeiter, lieferant);
				
				return teil;
			} else {
				log.error("ID : "+id+ " Teil konnte nicht gefunden werden.");
				return null;
			}
		} catch (SQLException e) {
			log.error("ID : "+id+ " Teil konnte nicht gefunden werden", e);
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
	public boolean teilKontrolle(long id) {
		if(findTeilById(id) != null) return true;
		else return false;
	}

	@Override
	public boolean updateTeil(Teil teil) {
		
		if(teilKontrolle(teil.getId())) {
			
			try {
				conn = DatabaseConnection.getMySQLConnection();
				PreparedStatement prStatement = conn.prepareStatement("UPDATE teil SET teilname=?,"
						+ "teilenummer=?, mitarbeiter_id=?, lieferant_id=? WHERE id=?");
				
				prStatement.setString(1, teil.getTeilname());
				prStatement.setString(2, teil.getTeilenummer());
				prStatement.setLong(3, teil.getMitarbeiter().getId());
				prStatement.setLong(4, teil.getLieferant().getId());
				prStatement.setLong(5, teil.getId());
				
				prStatement.executeUpdate();
				log.info("ID : "+teil.getId()+ " Teil wurde updated.");
				return true;
			} catch (SQLException e) {
				log.error("ID : "+teil.getId()+ "beim Update ein Fehler ist aufgetreten", e);
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
