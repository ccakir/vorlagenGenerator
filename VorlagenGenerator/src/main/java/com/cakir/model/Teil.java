package com.cakir.model;

public class Teil {
	
	private long id;
	
	private String teilname;
	
	private String teilenummer;
	
	private Mitarbeiter mitarbeiter;
	
	private Lieferant lieferant;
	
	

	public Teil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teil(String teilname, String teilenummer, Mitarbeiter mitarbeiter, Lieferant lieferant) {
		super();
		this.teilname = teilname;
		this.teilenummer = teilenummer;
		this.mitarbeiter = mitarbeiter;
		this.lieferant = lieferant;
	}

	public Teil(long id, String teilname, String teilenummer, Mitarbeiter mitarbeiter, Lieferant lieferant) {
		super();
		this.id = id;
		this.teilname = teilname;
		this.teilenummer = teilenummer;
		this.mitarbeiter = mitarbeiter;
		this.lieferant = lieferant;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTeilname() {
		return teilname;
	}

	public void setTeilname(String teilname) {
		this.teilname = teilname;
	}

	public String getTeilenummer() {
		return teilenummer;
	}

	public void setTeilenummer(String teilenummer) {
		this.teilenummer = teilenummer;
	}

	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}

	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	public Lieferant getLieferant() {
		return lieferant;
	}

	public void setLieferant(Lieferant lieferant) {
		this.lieferant = lieferant;
	}

	@Override
	public String toString() {
		return "Teil [id=" + id + ", teilname=" + teilname + ", teilenummer=" + teilenummer + ", mitarbeiter="
				+ mitarbeiter + ", lieferant=" + lieferant + "]";
	}
	
	

}
