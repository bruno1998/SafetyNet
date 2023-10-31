package com.database;

import java.util.List;

import com.database.model.Firestation;
import com.database.model.MedicalRecord;
import com.database.model.Person;

public class DataBase {
	
	private List<Firestation> firestations;
	private List<Person> persons;
	private List<MedicalRecord> medicalrecords;
	
	
	
	public DataBase(List<Firestation> firestations, List<Person> persons, List<MedicalRecord> medicalrecords) {
		this.firestations = firestations;
		this.persons = persons;
		this.medicalrecords = medicalrecords;
	}

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
				fs.setStation(firestation.getStation());
				break;
			}
		}
	}
	
	public void deleteFirestation(Firestation firestation) {
		Firestation tmp = null;
		for (Firestation firestationtmp : this.firestations) {
			if( firestation.getAddress().equals(firestationtmp.getAddress()) && firestation.getStation().equals(firestationtmp.getStation())) {
				tmp = firestationtmp;
				System.out.println(99);
				break;
			}
		}
		this.firestations.remove(tmp);
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
				p.setAddress(person.getAddress());
				p.setCity(person.getCity());
				p.setEmail(person.getEmail());
				p.setPhone(person.getPhone());;
				break;
			}
		}
	}

	public void deletePerson(Person person) {
		Person tmp = null;
		for (Person persontmp : this.persons) {
			if(persontmp.equals(person)){
				tmp = persontmp;
				break;
			}
		}
		this.persons.remove(tmp);
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
				mc.setAllergies(medicalRecord.getAllergies());
				mc.setBirthdate(medicalRecord.getBirthdate());
				mc.setMedications(medicalRecord.getMedications());;
				break;
			}
		}
	}
	
	public void deleteMedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord tmp = null;
		for (MedicalRecord medicalRecordtmp : this.medicalrecords) {
			if(medicalRecordtmp.equals(medicalRecord)){
				tmp = medicalRecordtmp;
				break;
			}
		}
		this.medicalrecords.remove(tmp);
	}
	
}
