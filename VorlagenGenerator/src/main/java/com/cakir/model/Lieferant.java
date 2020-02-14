package com.cakir.model;



public class Lieferant {
	
	
	private long id;
	
	
	private String name;
	
	private String tel;
	
	
	private Mitarbeiter mitarbeiter;

	
	public Lieferant() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}


	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}


	public Lieferant(long id, String name, String tel, Mitarbeiter mitarbeiter) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.mitarbeiter = mitarbeiter;
	}


	public Lieferant(String name, String tel, Mitarbeiter mitarbeiter) {
		super();
		this.name = name;
		this.tel = tel;
		this.mitarbeiter = mitarbeiter;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	

	
}
