package com.cakir.service;

import java.util.List;

import com.cakir.model.Lieferant;

public interface LieferantService {
	
	boolean speichernLieferant(Lieferant lieferant);
	
	List<Lieferant> alleLieferanten();
	
	boolean deleteLieferant(long id);
	
	Lieferant findLieferantById(long id);
	
	boolean lieferantKontrolle(long id);
	
	boolean updateLieferant(Lieferant lieferant);
	

}
