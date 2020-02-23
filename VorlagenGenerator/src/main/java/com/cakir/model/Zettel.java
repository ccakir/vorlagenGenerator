package com.cakir.model;

import java.time.LocalDate;
import java.util.Date;

public class Zettel {
	
	private long id;
	
	private String datum;
	
	private String grund;
	
	private String bemerkung;
	
	private String schicht;
	
	private String abteilung;
	
	private String maschiene;
	
	private int quantity;
	
	private Teil teil;
	
	private Mitarbeiter mitarbeiter;
	
	private String type;

	
	
	public Zettel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Zettel(String datum, String grund, String bemerkung, String schicht, String abteilung, String maschiene,
			int quantity, Teil teil, Mitarbeiter mitarbeiter, String type) {
		super();
		this.datum = datum;
		this.grund = grund;
		this.bemerkung = bemerkung;
		this.schicht = schicht;
		this.abteilung = abteilung;
		this.maschiene = maschiene;
		this.quantity = quantity;
		this.teil = teil;
		this.mitarbeiter = mitarbeiter;
		this.type = type;
	}

	public Zettel(long id, String datum, String grund, String bemerkung, String schicht, String abteilung,
			String maschiene, int quantity, Teil teil, Mitarbeiter mitarbeiter, String type) {
		super();
		this.id = id;
		this.datum = datum;
		this.grund = grund;
		this.bemerkung = bemerkung;
		this.schicht = schicht;
		this.abteilung = abteilung;
		this.maschiene = maschiene;
		this.quantity = quantity;
		this.teil = teil;
		this.mitarbeiter = mitarbeiter;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getGrund() {
		return grund;
	}

	public void setGrund(String grund) {
		this.grund = grund;
	}

	public String getBemerkung() {
		return bemerkung;
	}

	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}

	public String getSchicht() {
		return schicht;
	}

	public void setSchicht(String schicht) {
		this.schicht = schicht;
	}

	public String getAbteilung() {
		return abteilung;
	}

	public void setAbteilung(String abteilung) {
		this.abteilung = abteilung;
	}

	public String getMaschiene() {
		return maschiene;
	}

	public void setMaschiene(String maschiene) {
		this.maschiene = maschiene;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Teil getTeil() {
		return teil;
	}

	public void setTeil(Teil teil) {
		this.teil = teil;
	}

	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}

	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	public String getType() {
		return type;
	}

	public void setType(String string) {
		this.type = string;
	}

	@Override
	public String toString() {
		return "Zettel [id=" + id + ", datum=" + datum + ", grund=" + grund + ", bemerkung=" + bemerkung + ", schicht="
				+ schicht + ", abteilung=" + abteilung + ", maschiene=" + maschiene + ", quantity=" + quantity
				+ ", teil=" + teil + ", mitarbeiter=" + mitarbeiter + ", type=" + type + "]";
	}
	
	

}
