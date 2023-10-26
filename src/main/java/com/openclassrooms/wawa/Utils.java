package com.openclassrooms.wawa;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Utils {
	
	
	
	public static int age(String birthDate) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate bd = LocalDate.parse(birthDate,formatter);
		
		LocalDate currenDate = LocalDate.now();
		
		Period age = Period.between(bd, currenDate);
		
		return age.getYears();
	}

}
