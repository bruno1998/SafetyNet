package com.openclassrooms.wawa;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class PersonRepository extends JsonReader {
	
	private String nodeName = "persons";

	public PersonRepository(ObjectMapper objectMapper) {
		super(objectMapper);
		// TODO Auto-generated constructor stub
	}
	
	public List<Person> getAllPerson() throws JsonParseException, JsonMappingException, IOException{
		TypeReference type =  new TypeReference<List<Person>>() {};
		return (List<Person>) readJsonData(new TypeReference<List<Person>>() {},nodeName);
	}
	
	
	public List<Person> getListByAddress(String address) throws JsonParseException, JsonMappingException, IOException{
		List<Person> allPersons = this.getAllPerson();
		List<Person> persons= allPersons.stream().filter(person -> person.getAddress().equals(address)).toList();
		return persons;
	}
	
	
	
}
