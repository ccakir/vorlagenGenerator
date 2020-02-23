package com.cakir.service;

import java.util.List;

import com.cakir.model.Zettel;

public interface ZettelService {
	
	void speichernZettel(Zettel zettel);
	
	List<Zettel> alleZettel(String type);
	
	boolean deleteZettel(long id);
	
	Zettel findZettelById(long id);
	
	boolean zettelKontrolle(long id);
	
	boolean updateZettel(Zettel zettel);

}
