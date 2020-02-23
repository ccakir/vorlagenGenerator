package com.cakir.service;

import java.util.List;

import com.cakir.model.Teil;

public interface TeilService {
	
	boolean speichernTeil(Teil teil);
	
	List<Teil> alleTeile();
	
	boolean deleteTeil(long id);
	
	Teil findTeilById(long id);
	
	boolean teilKontrolle(long id);
	
	boolean updateTeil(Teil teil);

}
