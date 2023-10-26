package com.database;

import java.util.List;

import com.openclassrooms.wawa.Firestation;
import com.openclassrooms.wawa.MedicalRecord;
import com.openclassrooms.wawa.Person;

public class DataBase {
	
	private List<Firestation> firestations;
	private List<Person> persons;
	private List<MedicalRecord> medicalRecords;
	
	public List<Firestation> getFirestations() {
		return firestations;
	}
	
	public void setFirestations(List<Firestation> firestations) {
		this.firestations = firestations;
	}
	
	public List<Person> getPersons() {
		return persons;
	}
	
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}
	
	public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
	
	

}
