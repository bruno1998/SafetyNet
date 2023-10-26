package com.openclassrooms.wawa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class FirestationRepository extends JsonReader{
	
	private String nodeName="firestations";
	
	public FirestationRepository(ObjectMapper objectMapper) {
		super(objectMapper);
		// TODO Auto-generated constructor stub
	}
	public List<Firestation> getAllFirestation() throws JsonParseException, JsonMappingException, IOException{
		TypeReference type =  new TypeReference<List<Firestation>>() {};
		return (List<Firestation>) readJsonData(new TypeReference<List<Firestation>>() {},nodeName);
	}
	

	public List<Firestation> getFirestationByNumber(String firestationNumber) throws JsonParseException, JsonMappingException, IOException{
		
		List<Firestation> allFirestation = (List<Firestation>) this.getAllFirestation();
		List<Firestation> azeaze=allFirestation.stream().filter(firestation -> firestation.getStation().equals(firestationNumber)).toList();
		return azeaze;
	}
	
	public Firestation getFirestationByAddress(String address) throws JsonParseException, JsonMappingException, IOException {
		List<Firestation> allFirestation = (List<Firestation>) this.getAllFirestation();
		List<Firestation> azeaze=allFirestation.stream().filter(firestation -> firestation.getAddress().equals(address)).toList();
		return azeaze.get(0);
	}
	
	


}
