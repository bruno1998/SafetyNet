package com.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.model.MedicalRecord;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.repository.FirestationRepository;
import com.repository.MedicalRecordRepository;
import com.repository.PersonRepository;

@Service
public class MedicalRecordService{

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	@Autowired
	private FirestationRepository fireStationRepository;
	
	
	public MedicalRecord getByStationNumber(String firstName, String lastName) throws JsonParseException, JsonMappingException, IOException{
		return medicalRecordRepository.getListFirstNameAndLastName(firstName, lastName);
	}
	

	public Boolean createMedicalRecord(MedicalRecord medicalRecord) throws IOException {
		medicalRecordRepository.createMedicalRecord(medicalRecord);
		return true;
	}
	
	public Boolean updateMedicalRecord(MedicalRecord medicalRecord) throws IOException {
		medicalRecordRepository.updateMedicalRecord(medicalRecord);
		return true;
	}
	
	public Boolean deleteMedicalRecord(MedicalRecord medicalRecord) throws IOException {
		medicalRecordRepository.deleteMedicalRecord(medicalRecord);
		return true;
	}
}
