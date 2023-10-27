package com.database;

import java.util.List;

import com.database.model.Firestation;
import com.database.model.MedicalRecord;
import com.database.model.Person;

public class DataBase {
	
	private List<Firestation> firestations;
	private List<Person> persons;
	private List<MedicalRecord> medicalrecords;
	
	public List<Firestation> getFirestations() {
		return firestations;
	}
	
	public void setFirestations(List<Firestation> firestations) {
		this.firestations = firestations;
	}
	
	public void addFirestation(Firestation firestation) {
		this.firestations.add(firestation);
	}

	public void updateFirestation(Firestation firestation) {
		for (Firestation fs : this.firestations) {
			if (fs.getAddress().equals(firestation.getAddress())) {
				fs.setStation(firestation.getAddress());
				break;
			}
		}
	}
	
	public void deleteFirestation(Firestation firestation) {
		this.firestations.remove(firestation);
	}
	
	public List<Person> getPersons() {
		return persons;
	}
	
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public void addPerson(Person person) {
		this.persons.add(person);
	}

	public void updatePerson(Person person) {
		for (Person p : this.persons) {
			if (p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
				p = person;
				break;
			}
		}
	}

	public void deletePerson(Person person) {
		this.persons.remove(person);
	}
	public List<MedicalRecord> getMedicalRecords() {
		return medicalrecords;
	}
	
	public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
		this.medicalrecords = medicalRecords;
	}
	
	public void addMedicalRecord(MedicalRecord medicalRecords) {
		this.medicalrecords.add(medicalRecords);
	}
	
	public void updateMedicalRecord(MedicalRecord medicalRecord) {
		for (MedicalRecord mc : this.medicalrecords) {
			if (mc.getFirstName().equals(medicalRecord.getFirstName()) && mc.getLastName().equals(medicalRecord.getLastName())) {
				mc = medicalRecord;
				break;
			}
		}
	}
	
	public void deleteMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalrecords.remove(medicalRecord);
	}
	
}
