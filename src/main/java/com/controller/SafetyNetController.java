package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.database.model.Firestation;
import com.database.model.MedicalRecord;
import com.database.model.Person;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.repository.PersonRepository;
import com.service.FirestationService;
import com.service.MedicalRecordService;
import com.service.PersonService;



@RestController
public class SafetyNetController {


	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private MedicalRecordService medicalRecordService;

	@Autowired
	private FirestationService firestationService;
	
	@GetMapping("/hello")
	@ResponseBody
	public String sayHello() throws IOException {	
		return "hello";
	}
	
	@GetMapping("/firestation")
	@ResponseBody
	@Transactional
	public List<Map<String, String>> getFirestationByNumber(@RequestParam("stationNumber") String stationNumber) throws JsonParseException, JsonMappingException, NumberFormatException, IOException {
		System.out.println("get the list of people covered by the firestation number : "+stationNumber);
		List<Map<String,String>> retour = firestationService.getByStationNumber(stationNumber);
		System.out.println(retour);
		return retour;
	}
	
	@GetMapping("childAlert")
	@ResponseBody
	public List<Map<String, String>> getListChildMissing(@RequestParam("address") String address) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("get the list of people from the address "+address+" if there is a child registered in that house");
		List<Map<String,String>> retour = personService.getListPersonByAddress(address); 
		return retour;
	}
	
	@GetMapping("phoneAlert")
	@ResponseBody
	public List<String> getListOfPhoneNumberNearFirestation(@RequestParam("firestation") String firestation_number) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("get the list of phone number from people covered by the firestation number "+firestation_number);
		List<String> retour = firestationService.getPersonNumberByFirestationNumber(firestation_number);
		return retour;
	}
		
	@GetMapping("fire")
	@ResponseBody
	public List<Map<String,Object>> getPeopleAndFirestationByAddress(@RequestParam("address") String address) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("get the list of people with their medical record from the address "+address);
		List<Map<String,Object>> retour = personService.getListPersonByAddressWithMedicalRecord(address);
		System.out.println(retour);
		return retour;

	}
	
	@GetMapping("flood")
	@ResponseBody
	public Map<String,Object> getListOfPeopleAndMedicalRecordByStation(@RequestParam("stations") List<String> stations) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("get the list of people covered by the firestation number +"stations);
		Map<String,Object> retour = personService.getListOfPeopleSortedByAddressCoveredByThoseStations(stations);
		System.out.println(retour);
		
		return retour;
	}
	
	@GetMapping("personInfo")
	@ResponseBody
	public List<Map<String,Object>> getListOfPeopleAndMedicalRecordByFirstAndLastName(@RequestParam("firstName") String firstname, @RequestParam("lastName") String lastname) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("get the personal including the medicalrecord of ")+firstname +" "+ lastname);
		List<Map<String,Object>> retour = personService.getPersonnalInformationOfPeople(firstname,lastname);
		System.out.println(retour);
		return retour;
	}
	
	@GetMapping("communityEmail")
	@ResponseBody
	public List<String> getListOfEmailOfAllPeopleByCity(@RequestParam("city") String city) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("get the list of email from the people inside the city "+city);
		List<String> retour =  personService.getAllEmailFromCity(city);
		return retour;
	}
	
	

    @PostMapping("person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) throws IOException {
    	System.out.println("create the person");
    	personService.createPerson(person);
    	return ResponseEntity.ok(person);
    }

    @PutMapping("person")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) throws IOException {
    	System.out.println("update the person");
    	personService.updatePerson(person);
    	return ResponseEntity.ok(person);
    }

    @DeleteMapping("person")
    public ResponseEntity<Person> deletePreson(@RequestHeader("firstName") String firstName, @RequestHeader("lastName") String lastName) throws IOException {
    	System.out.println("delete the person firstname : " +firstName+" / lastname : "+lastName);
    	Person p = new Person();
    	p.setLastName(lastName);
    	p.setFirstName(firstName);
    	personService.deletePerson(p);
    	return ResponseEntity.ok(p);
    }
    
    

    @PostMapping("firestation")
    public ResponseEntity<Firestation> createFirestation(@RequestBody Firestation firestation) throws IOException {
		System.out.println("create the firestation " +firestation.getStation()+ " / " + firestation.getAddress());
    	firestationService.createFirestation(firestation);
    	return ResponseEntity.ok(firestation);

    }

    @PutMapping("firestation")
    public ResponseEntity<Firestation> updateFirestation(@RequestBody Firestation firestation) throws IOException {
    	System.out.println("update the firestation for the address" +firestation.getAddress());
    	firestationService.updateFirestation(firestation);
    	return ResponseEntity.ok(firestation);
    }

    @DeleteMapping("firestation")
    public ResponseEntity<Firestation> deleteFirestation(@RequestHeader("station") String station, @RequestHeader("address") String address) throws IOException {  	
    	System.out.println("delete the firestation station : "+ station+" / "+"address : "+address);
    	Firestation fs = new Firestation();
    	fs.setAddress(address);
    	fs.setStation(station);
    	firestationService.deleteFirestation(fs);
    	return ResponseEntity.ok(fs);
    }
    
    

    @PostMapping("medicalRecord")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
    	System.out.println("create the medicalRecord for "+medicalRecord.getLastName()+ " / " +medicalRecord.getFirstName());
    	medicalRecordService.createMedicalRecord(medicalRecord);
    	return ResponseEntity.ok(medicalRecord);
    }

    @PutMapping("medicalRecord")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
    	System.out.println("upadte the medicalRecord for "+medicalRecord.getLastName()+ " / " +medicalRecord.getFirstName());
    	medicalRecordService.updateMedicalRecord(medicalRecord);
    	return ResponseEntity.ok(medicalRecord);
    }

    @DeleteMapping("medicalRecord")
    public ResponseEntity<MedicalRecord> deleteMedicalRecord(@RequestHeader("firstName") String firstName, @RequestHeader("lastName") String lastName) throws IOException {
    	System.out.println("delete the medicalRecord for "++medicalRecord.getLastName()+ " / " +medicalRecord.getFirstName());
    	MedicalRecord medicalRecord = medicalRecordService.getByStationNumber(firstName, lastName);
    	medicalRecordService.deleteMedicalRecord(medicalRecord);
    	return ResponseEntity.ok(medicalRecord);
    }
	
	
	
	
	
	
	
	
	
	

}
