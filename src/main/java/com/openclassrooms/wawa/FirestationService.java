package com.openclassrooms.wawa;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.transaction.annotation.*;

@Service
public class FirestationService {
	
	@Autowired
	private FirestationRepository firestationRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	
	public List<Map<String, String>> getByStationNumber(String numberStation) throws JsonParseException, JsonMappingException, IOException{
		
		Map<String,String>monretourtemporaire = new HashMap<String,String>();
		monretourtemporaire.put("nom", "charle");
		monretourtemporaire.put("prénom","henrie");
		monretourtemporaire.put("age","17");
		monretourtemporaire.put("numéro de téléphone","0654525474");
		List<Map<String,String>>listmonretourtemporaire = new ArrayList<Map<String,String>>();
		listmonretourtemporaire.add(monretourtemporaire);
		listmonretourtemporaire.add(monretourtemporaire);
		
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
			String tmp = "firstname : "+person.getFirstName()+" / lastname : "+person.getLastName()+" / phone number : "+person.getPhone();
			retour.add(tmp);
			MedicalRecord mc = medicalRecordRepository.getListFirstNameAndLastName(person.getFirstName(), person.getLastName());

			if (Utils.age(mc.getBirthdate()) > 18) {
				adulte++;
			}else {
				enfant++;
			}
		}
		monretourtemporaire = new HashMap<String,String>();
		monretourtemporaire.put(" / nombre d'adulte : ",String.valueOf(adulte));
		monretourtemporaire.put(" / nombre d'enfant : ",String.valueOf(enfant));
		listmonretourtemporaire.add(monretourtemporaire);
		return listmonretourtemporaire;
	}
	
	public List<String> getPersonNumberByFirestationNumber(String firestation_number) throws JsonParseException, JsonMappingException, IOException{
		
		List<Firestation> listFirestations = firestationRepository.getFirestationByNumber(firestation_number);
		List<Person> listPersons = new ArrayList<Person>();
		
		for (Firestation firestation : listFirestations) {
			listPersons.addAll(personRepository.getListByAddress(firestation.getAddress()));
		}
		List<String> listPhoneNumber = new ArrayList<String>();
		for (Person person : listPersons) {
			System.out.println(person.getPhone());
			listPhoneNumber.add(person.getPhone());
		}
		
		return listPhoneNumber;
	}
	
	
	public String getFirestationNumberByAddress(String address) throws JsonParseException, JsonMappingException, IOException {
		Firestation firestation = firestationRepository.getFirestationByAddress(address);
		return firestation.getStation();
	}

}
