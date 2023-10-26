package com.openclassrooms.wawa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class PersonService {

	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	
	public ArrayList<String> getListPersonByAddress(String address) throws JsonParseException, JsonMappingException, IOException{		
		
		List<Person> listPersons = personRepository.getListByAddress(address); 
		
		ArrayList<String> retour = new ArrayList<String>();
		ArrayList<String> adulte = new ArrayList<String>();
		
		boolean enfant = false;
		for (Person person : listPersons) {
			int age = Utils.age(medicalRecordRepository.getListFirstNameAndLastName(person.getFirstName(), person.getLastName()).getBirthdate());
			if (age<18) {
				retour.add("firstName : "+person.getFirstName()+" / lastName : "+person.getLastName()+" / age : "+ age);
				enfant = true;
			}else {
				adulte.add("firstName : "+person.getFirstName()+" / lastName : " + person.getLastName());
			}
		}
		if (enfant) {
			retour.addAll(adulte);
			return retour;
		}
		return null;
	}
	
	public List<String> getListPersonByAddressWithMedicalRecord(String address) throws JsonParseException, JsonMappingException, IOException{

		List<Person> listPersons = personRepository.getListByAddress(address); 
		
		return null;
	}
	
}
