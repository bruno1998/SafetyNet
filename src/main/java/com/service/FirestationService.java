package com.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.model.Firestation;
import com.database.model.MedicalRecord;
import com.database.model.Person;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.repository.PersonRepository;
import com.util.Utils;
import com.repository.FirestationRepository;
import com.repository.MedicalRecordRepository;


import org.springframework.transaction.annotation.*;

@Service
public class FirestationService {
	
	@Autowired
	public FirestationRepository firestationRepository;
	
	@Autowired
	public PersonRepository personRepository;
	
	@Autowired
	public MedicalRecordRepository medicalRecordRepository;
	
		
	
	public List<Map<String, String>> getByStationNumber(String numberStation) throws JsonParseException, JsonMappingException, IOException{
		
		List<Map<String,String>>listmonretour = new ArrayList<Map<String,String>>();
		
		ArrayList<String> retour = new ArrayList<>();
		int adulte = 0;
		int enfant = 0;
		
		List<Firestation> listFirestation = firestationRepository.getFirestationByNumber(numberStation);
		
		List<String> listAddress = new ArrayList<>();
		for (Firestation firestation : listFirestation) {
			listAddress.add(firestation.getAddress());
		}
				
		List<Person> listPerson = new ArrayList<>();
		
		for (String address : listAddress) {
			List<Person> tmp = new ArrayList<>();
			tmp = personRepository.getListByAddress(address);	
			listPerson.addAll(tmp);
		}
		
		List<MedicalRecord> listMedicalRecord = new ArrayList<>();
		
		for(Person person : listPerson) {
			Map<String,String> people = new HashMap<String,String>();
			people.put("firstname", person.getFirstName());
			people.put("lastname", person.getLastName() );
			people.put("phone number", person.getPhone());
			listmonretour.add(people);
			MedicalRecord mc = medicalRecordRepository.getListFirstNameAndLastName(person.getFirstName(), person.getLastName());
			if (Utils.age(mc.getBirthdate()) > 18) {
				adulte++;
			}else {
				enfant++;
			}
		}
		Map<String,String> numberAdultChildren = new HashMap<String,String>();
		numberAdultChildren.put(" / nombre d'adulte : ",String.valueOf(adulte));
		numberAdultChildren.put(" / nombre d'enfant : ",String.valueOf(enfant));
		listmonretour.add(numberAdultChildren);
		return listmonretour;
	}
	
	public List<String> getPersonNumberByFirestationNumber(String firestation_number) throws JsonParseException, JsonMappingException, IOException{
		
		List<Firestation> listFirestations = firestationRepository.getFirestationByNumber(firestation_number);
		List<Person> listPersons = new ArrayList<Person>();
		
		for (Firestation firestation : listFirestations) {
			listPersons.addAll(personRepository.getListByAddress(firestation.getAddress()));
		}
		List<String> listPhoneNumber = new ArrayList<String>();
		for (Person person : listPersons) {
			listPhoneNumber.add(person.getPhone());
		}
		
		return listPhoneNumber;
	}
	
	
	public String getFirestationNumberByAddress(String address) throws JsonParseException, JsonMappingException, IOException {
		Firestation firestation = firestationRepository.getFirestationByAddress(address);
		return firestation.getStation();
	}
	

	public Boolean createFirestation(Firestation firestation) throws IOException {
		firestationRepository.createFirestation(firestation);
		return true;
	}
	
	public Boolean updateFirestation(Firestation firestation) throws IOException {
		firestationRepository.updateFirestation(firestation);
		return true;
	}
	
	public Boolean deleteFirestation(Firestation firestation) throws IOException {
		firestationRepository.deleteFirestation(firestation);
		return true;
	}

}
