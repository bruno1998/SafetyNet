package com.openclassrooms.wawa.service;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.controller.DemoApplication;
import com.database.model.MedicalRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.FirestationRepository;
import com.repository.MedicalRecordRepository;
import com.repository.PersonRepository;
import com.service.MedicalRecordService;

@SpringBootTest(classes = DemoApplication.class)
public class MedicalRecordServiceTest {

	
	@Test
	void medicalRecordTest() throws IOException {
		ObjectMapper objectMappper = new ObjectMapper();

		MedicalRecordService medicalRecordService = new MedicalRecordService();
		medicalRecordService.firestationRepository = new FirestationRepository(objectMappper);
		medicalRecordService.medicalRecordRepository = new MedicalRecordRepository(objectMappper);
		medicalRecordService.personRepository = new PersonRepository(objectMappper);

		MedicalRecord mr = new MedicalRecord();
		mr.setFirstName("bruno");
		mr.setLastName("mazel");
		mr.setBirthdate("01/01/2021");
		mr.setAllergies(new ArrayList<String>());
		mr.setMedications(new ArrayList<String>());

		medicalRecordService.createMedicalRecord(mr);
		mr.setBirthdate("10/10/2021");
		medicalRecordService.updateMedicalRecord(mr);
		medicalRecordService.deleteMedicalRecord(mr);
	}

}
