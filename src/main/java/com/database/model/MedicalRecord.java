package com.database.model;

import java.util.List;

public class MedicalRecord {

	private String firstName;
	private String lastName;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	public List<String> getAllergies() {
		return allergies;
	}
	
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	
	public List<String> getMedications() {
		return medications;
	}
	
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	
	public boolean equals(MedicalRecord mr) {
		return (this.getLastName().equals(mr.getLastName()) && this.getFirstName().equals(mr.getFirstName()));
	}

}
