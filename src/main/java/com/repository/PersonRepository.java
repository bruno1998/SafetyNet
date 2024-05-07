package com.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.database.DataBase;
import com.database.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.JsonReader;

@Repository
public class PersonRepository extends JsonReader {

	private String nodeName = "persons";

	public PersonRepository(ObjectMapper objectMapper) {
		super(objectMapper);
		// TODO Auto-generated constructor stub
	}

	public List<Person> getAllPerson() throws IOException {
		return (List<Person>) readJsonData(new TypeReference<List<Person>>() {}, nodeName);
	}


	public List<Person> getListByAddress(String address) throws IOException {
		List<Person> allPersons = this.getAllPerson();
		List<Person> persons =
				allPersons.stream().filter(person -> person.getAddress().equals(address)).toList();
		return persons;
	}

	public List<Person> getListByAddress(String firstname, String lastname) throws IOException {
		List<Person> allPersons = this.getAllPerson();
		List<Person> persons =
				allPersons.stream().filter(person -> person.getFirstName().equals(firstname)
						&& person.getLastName().equals(lastname)).toList();
		return persons;
	}

	public List<Person> getListPersonByCity(String city) throws IOException {
		List<Person> allPersons = this.getAllPerson();
		List<Person> persons =
				allPersons.stream().filter(person -> person.getCity().equals(city)).toList();
		return persons;

	}


	public Boolean createPerson(Person person) throws IOException {

		DataBase db = getDB();
		db.addPerson(person);
		this.updateDB(db);
		return true;
	}


	public Boolean updatePerson(Person person) throws IOException {
		DataBase db = getDB();
		db.updatePerson(person);
		this.updateDB(db);
		return true;
	}


	public Boolean deletePerson(Person person) throws IOException {
		DataBase db = getDB();
		db.deletePerson(person);
		this.updateDB(db);
		return true;
	}


}
