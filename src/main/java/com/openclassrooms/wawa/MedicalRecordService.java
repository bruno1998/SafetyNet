package com.openclassrooms.wawa;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class MedicalRecordService{

	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	
	public MedicalRecord getByStationNumber(String firstName, String lastName) throws JsonParseException, JsonMappingException, IOException{
		return medicalRecordRepository.getListFirstNameAndLastName(firstName, lastName);
	}
	
}
