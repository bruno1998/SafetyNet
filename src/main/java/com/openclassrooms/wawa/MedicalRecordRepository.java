package com.openclassrooms.wawa;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class MedicalRecordRepository extends JsonReader {
	
	private String nodeName = "medicalrecords";

	public MedicalRecordRepository(ObjectMapper objectMapper) {
		super(objectMapper);
		// TODO Auto-generated constructor stub
	}
	
	public List<MedicalRecord> getAllMedicalRecord() throws JsonParseException, JsonMappingException, IOException{
		TypeReference type =  new TypeReference<List<MedicalRecord>>() {};
		return (List<MedicalRecord>) readJsonData(new TypeReference<List<MedicalRecord>>() {},nodeName);
	}
	
	
	public MedicalRecord getListFirstNameAndLastName(String firstName, String lastName) throws JsonParseException, JsonMappingException, IOException{
		MedicalRecord medicalRecords= this.getAllMedicalRecord().stream().filter(medicalRecord-> medicalRecord.getFirstName().equals(firstName)  && medicalRecord.getLastName().equals(lastName)).toList().get(0);
		return medicalRecords;
	}
	
	
	
}
