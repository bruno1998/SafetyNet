package com.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.model.MedicalRecord;
import com.repository.FirestationRepository;
import com.repository.MedicalRecordRepository;
import com.repository.PersonRepository;

@Service
public class MedicalRecordService {

	@Autowired
	public PersonRepository personRepository;

	@Autowired
	public MedicalRecordRepository medicalRecordRepository;

	@Autowired
	public FirestationRepository firestationRepository;


	public MedicalRecord getByStationNumber(String firstName, String lastName) throws IOException {
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
