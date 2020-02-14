package com.cakir.service;

import java.util.List;

import com.cakir.model.Mitarbeiter;

public interface MitarbeiterService {

	boolean speichernMitarbeiter(Mitarbeiter mitarbeiter);
	
	List<Mitarbeiter> alleMitarbeiter();
	
	boolean deleteMitarbeiter(long id);
	
	Mitarbeiter findMitarbeiterById(long id);
	
	boolean mitarbeiterKontrolle(long id);
	
	boolean updateMitarbeiter(Mitarbeiter mitarbeiter);
}
