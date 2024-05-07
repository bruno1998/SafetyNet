package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.database.model.Person;
import com.service.PersonService;

@RestController
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	public PersonService personService;

	@GetMapping("/childAlert")
	@ResponseBody
	public List<Map<String, String>> getListChildMissing(@RequestParam("address") String address){
		logger.info("get the list of people from the address " + address+ " if there is a child registered in that house");
		List<Map<String, String>> retour = null;
		try {retour = personService.getListPersonByAddress(address);
		} catch(IOException e) {
			logger.error(e.toString());
		}
		logger.info(retour.toString());
		return retour;
	}
	
	@GetMapping("/fire")
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

	@GetMapping("/flood")
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

	@GetMapping("/personInfo")
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

	@GetMapping("/communityEmail")
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

	@PostMapping("/person")
	public ResponseEntity<Person> createPerson(@RequestBody Person person){
		logger.info("create the person");
		try {personService.createPerson(person);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(person.toString());
		return ResponseEntity.ok(person);
	}

	@PutMapping("/person")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person){
		logger.info("update the person firstname : " + person.getFirstName() + " lastname : "+ person.getLastName());
		try {personService.updatePerson(person);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(person.toString());
		return ResponseEntity.ok(person);
	}

	@DeleteMapping("/person")
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
}
