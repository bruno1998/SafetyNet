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


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.database.model.Firestation;
import com.database.model.MedicalRecord;
import com.database.model.Person;

import com.repository.PersonRepository;
import com.service.FirestationService;
import com.service.MedicalRecordService;
import com.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class SafetyNetController {

	private static final Logger logger = LoggerFactory.getLogger(SafetyNetController.class);

	@Autowired
	public PersonService personService;

	@Autowired
	public MedicalRecordService medicalRecordService;

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
	public List<Map<String, String>> getFirestationByNumber(@RequestParam("stationNumber") String stationNumber)
			{
		logger.info("get the list of people covered by the firestation number : " + stationNumber);
		List<Map<String, String>> retour = null;
		try {retour = firestationService.getByStationNumber(stationNumber);
		}catch (NumberFormatException|IOException e) {
			logger.error(e.toString());
		}
		logger.info(retour.toString());
		return retour;
	}

	@GetMapping("childAlert")
	@ResponseBody
	public List<Map<String, String>> getListChildMissing(@RequestParam("address") String address){
		logger.info("get the list of people from the address " + address+ " if there is a child registered in that house");
		List<Map<String, String>> retour = null;
		try {personService.getListPersonByAddress(address);
		} catch(IOException e) {
			logger.error(e.toString());
		}
		logger.info(retour.toString());
		return retour;
	}

	@GetMapping("phoneAlert")
	@ResponseBody
	public List<String> getListOfPhoneNumberNearFirestation(@RequestParam("firestation") String firestation_number){
		logger.info("get the list of phone number from people covered by the firestation number "+ firestation_number);
		List<String> retour = null ;
		try {retour = firestationService.getPersonNumberByFirestationNumber(firestation_number);
		}catch (IOException e) {
			logger.error(e.toString());
		}
				
		logger.info(retour.toString());
		return retour;
	}

	@GetMapping("fire")
	@ResponseBody
	public List<Map<String, Object>> getPeopleAndFirestationByAddress(@RequestParam("address") String address){
		logger.info("get the list of people with their medical record from the address " + address);
		List<Map<String, Object>> retour =null;
		try {retour = personService.getListPersonByAddressWithMedicalRecord(address);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(retour.toString());
		return retour;

	}

	@GetMapping("flood")
	@ResponseBody
	public Map<String, Object> getListOfPeopleAndMedicalRecordByStation(@RequestParam("stations") List<String> stations){
		logger.info("get the list of people covered by the firestation number +" + stations);
		Map<String, Object> retour = null;
		try {retour = personService.getListOfPeopleSortedByAddressCoveredByThoseStations(stations);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(retour.toString());
		return retour;
	}

	@GetMapping("personInfo")
	@ResponseBody
	public List<Map<String, Object>> getListOfPeopleAndMedicalRecordByFirstAndLastName(@RequestParam("firstName") String firstname, @RequestParam("lastName") String lastname){
		logger.info(
				"get the personal including the medicalrecord of " + firstname + " " + lastname);
		List<Map<String, Object>> retour = null;
		try {retour = personService.getPersonnalInformationOfPeople(firstname, lastname);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(retour.toString());
		return retour;
	}

	@GetMapping("communityEmail")
	@ResponseBody
	public List<String> getListOfEmailOfAllPeopleByCity(@RequestParam("city") String city){
		logger.info("get the list of email from the people inside the city " + city);
		List<String> retour = null;
		try {retour = personService.getAllEmailFromCity(city);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(retour.toString());
		return retour;
	}



	@PostMapping("person")
	public ResponseEntity<Person> createPerson(@RequestBody Person person){
		logger.info("create the person");
		try {personService.createPerson(person);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(person.toString());
		return ResponseEntity.ok(person);
	}

	@PutMapping("person")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person){
		logger.info("update the person firstname : " + person.getFirstName() + " lastname : "+ person.getLastName());
		try {personService.updatePerson(person);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(person.toString());
		return ResponseEntity.ok(person);
	}

	@DeleteMapping("person")
	public ResponseEntity<Person> deletePreson(@RequestHeader("firstName") String firstName, @RequestHeader("lastName") String lastName){
		logger.info("delete the person firstname : " + firstName + " / lastname : " + lastName);
		Person p = new Person();
		p.setLastName(lastName);
		p.setFirstName(firstName);
		try {personService.deletePerson(p);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		return ResponseEntity.ok(p);
	}



	@PostMapping("firestation")
	public ResponseEntity<Firestation> createFirestation(@RequestBody Firestation firestation){
		logger.info("create the firestation " + firestation.getStation() + " / "+ firestation.getAddress());
		try {firestationService.createFirestation(firestation);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(firestation.toString());
		return ResponseEntity.ok(firestation);

	}

	@PutMapping("firestation")
	public ResponseEntity<Firestation> updateFirestation(@RequestBody Firestation firestation){
		logger.info("update the firestation for the address" + firestation.getAddress());
		try {firestationService.updateFirestation(firestation);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(firestation.toString());
		return ResponseEntity.ok(firestation);
	}

	@DeleteMapping("firestation")
	public ResponseEntity<Firestation> deleteFirestation(@RequestHeader("station") String station, @RequestHeader("address") String address){
		logger.info("delete the firestation station : " + station + " / " + "address : " + address);
		Firestation fs = new Firestation();
		fs.setAddress(address);
		fs.setStation(station);
		try {firestationService.deleteFirestation(fs);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		return ResponseEntity.ok(fs);
	}



	@PostMapping("medicalRecord")
	public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord){
		logger.info("create the medicalRecord for " + medicalRecord.getLastName() + " / "+ medicalRecord.getFirstName());
		try {medicalRecordService.createMedicalRecord(medicalRecord);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(medicalRecord.toString());
		return ResponseEntity.ok(medicalRecord);
	}

	@PutMapping("medicalRecord")
	public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord){
		logger.info("update the medicalRecord for " + medicalRecord.getLastName() + " / "+ medicalRecord.getFirstName());
		try {medicalRecordService.updateMedicalRecord(medicalRecord);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(medicalRecord.toString());
		return ResponseEntity.ok(medicalRecord);
	}

	@DeleteMapping("medicalRecord")
	public ResponseEntity<MedicalRecord> deleteMedicalRecord(@RequestHeader("firstName") String firstName, @RequestHeader("lastName") String lastName){
		logger.info("delete the medicalRecord firstname : " + firstName + " / lastname : " + lastName);
		MedicalRecord medicalRecord = null;
		try {medicalRecord = medicalRecordService.getByStationNumber(firstName, lastName);
			medicalRecordService.deleteMedicalRecord(medicalRecord);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		return ResponseEntity.ok(medicalRecord);
	}



}
