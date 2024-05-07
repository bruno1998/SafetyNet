package com.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.model.Firestation;
import com.database.model.Person;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.repository.PersonRepository;
import com.util.Utils;
import com.repository.FirestationRepository;
import com.repository.MedicalRecordRepository;

@Service
public class PersonService {


	@Autowired
	public PersonRepository personRepository;

	@Autowired
	public MedicalRecordRepository medicalRecordRepository;

	@Autowired
	public FirestationRepository firestationRepository;

	public List<Map<String, String>> getListPersonByAddress(String address)
			throws IOException {

		List<Person> listPersons = personRepository.getListByAddress(address);

		List<Map<String, String>> retour = new ArrayList<Map<String, String>>();


		boolean enfant = false;
		for (Person person : listPersons) {
			Map<String, String> people = new HashMap<String, String>();
			int age = Utils.age(medicalRecordRepository
					.getListFirstNameAndLastName(person.getFirstName(), person.getLastName())
					.getBirthdate());
			people.put("firstname", person.getFirstName());
			people.put("lastname", person.getLastName());
			if (age < 18) {
				people.put("age", String.valueOf(age));
				enfant = true;
			}
			retour.add(people);
		}
		if (enfant) {
			return retour;
		}
		return null;
	}

	public List<Map<String, Object>> getListPersonByAddressWithMedicalRecord(String address)
			throws IOException {

		List<Map<String, Object>> retour = new ArrayList<Map<String, Object>>();

		List<Person> listPersons = personRepository.getListByAddress(address);
		for (Person person : listPersons) {
			Map<String, Object> people = new HashMap<String, Object>();
			int age = Utils.age(medicalRecordRepository
					.getListFirstNameAndLastName(person.getFirstName(), person.getLastName())
					.getBirthdate());
			people.put("firstname", person.getFirstName());
			people.put("lastname", person.getLastName());
			people.put("phone number", person.getPhone());
			people.put("age", String.valueOf(age));
			people.put("allergie", medicalRecordRepository
					.getListFirstNameAndLastName(person.getFirstName(), person.getLastName())
					.getAllergies());
			people.put("médicament", medicalRecordRepository
					.getListFirstNameAndLastName(person.getFirstName(), person.getLastName())
					.getMedications());
			retour.add(people);
		}
		return retour;
	}

	public Map<String, Object> getListOfPeopleSortedByAddressCoveredByThoseStations(
			List<String> stationsNumber)
			throws IOException {

		Map<String, Object> retour = new HashMap<String, Object>();

		for (String stationNumber : stationsNumber) {
			List<Firestation> listFirestation =
					firestationRepository.getFirestationByNumber(stationNumber);
			for (Firestation firestation : listFirestation) {
				retour.put(firestation.getAddress(),
						this.getListPersonByAddressWithMedicalRecord(firestation.getAddress()));
			}
		}
		return retour;
	}

	public List<Map<String, Object>> getPersonnalInformationOfPeople(String firstName,
			String lastname) throws IOException {
		List<Map<String, Object>> retour = new ArrayList<Map<String, Object>>();

		List<Person> persons = personRepository.getListByAddress(firstName, lastname);
		for (Person person : persons) {
			Map<String, Object> people = new HashMap<String, Object>();
			people.put("fistname", person.getFirstName());
			people.put("lastname", person.getLastName());
			people.put("address", person.getAddress());
			people.put("age", String.valueOf(Utils.age(medicalRecordRepository
					.getListFirstNameAndLastName(person.getFirstName(), person.getLastName())
					.getBirthdate())));
			people.put("email", person.getEmail());
			people.put("allergie", medicalRecordRepository
					.getListFirstNameAndLastName(person.getFirstName(), person.getLastName())
					.getAllergies());
			people.put("médicament", medicalRecordRepository
					.getListFirstNameAndLastName(person.getFirstName(), person.getLastName())
					.getMedications());
			retour.add(people);
		}
		return retour;
	}

	public List<String> getAllEmailFromCity(String city)
			throws IOException {
		List<String> retour = new ArrayList<String>();
		List<Person> persons = personRepository.getListPersonByCity(city);
		for (Person person : persons) {
			retour.add(person.getEmail());
		}
		return retour;
	}

	public Boolean createPerson(Person person) throws IOException {
		personRepository.createPerson(person);
		return true;
	}

	public Boolean updatePerson(Person person) throws IOException {
		personRepository.updatePerson(person);
		return true;
	}

	public Boolean deletePerson(Person person) throws IOException {
		personRepository.deletePerson(person);
		return true;
	}
}
