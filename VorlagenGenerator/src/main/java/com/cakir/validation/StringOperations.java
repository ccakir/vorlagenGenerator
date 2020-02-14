package com.cakir.validation;

public class StringOperations {
	
	public String firstCharToUpperCase(String text) {
		
		return text.substring(0, 1).toUpperCase() + text.substring(1);
	}

}
