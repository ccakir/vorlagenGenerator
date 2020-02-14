package com.cakir.model;



public class Mitarbeiter {

	
	private long id;

	private String vorname;

	private String nachname;

	private String email;

	private String tel;

	
	public Mitarbeiter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mitarbeiter(String vorname, String nachname, String email, String tel) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.tel = tel;
	}
	

	public Mitarbeiter(long id, String vorname, String nachname, String email, String tel) {
		super();
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.tel = tel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Mitarbeiter [id=" + id + ", vorname=" + vorname + ", nachname=" + nachname + ", email=" + email
				+ ", tel=" + tel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mitarbeiter other = (Mitarbeiter) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
