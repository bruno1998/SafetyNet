package com.openclassrooms.wawa.service;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.controller.DemoApplication;
import com.database.model.Firestation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.FirestationRepository;
import com.repository.MedicalRecordRepository;
import com.repository.PersonRepository;
import com.service.FirestationService;

@SpringBootTest(classes = DemoApplication.class)
public class FirestationServiceTest {

	@Test
	void fireStationTest() throws IOException {
		ObjectMapper objectMappper = new ObjectMapper();
		FirestationService firestationService = new FirestationService();
		firestationService.firestationRepository = new FirestationRepository(objectMappper);
		firestationService.medicalRecordRepository = new MedicalRecordRepository(objectMappper);
		firestationService.personRepository = new PersonRepository(objectMappper);

		Firestation firestation = new Firestation();
		firestation.setAddress("38 rue du puits");
		firestation.setStation("15");

		firestationService.createFirestation(firestation);
		firestation.setStation("16");
		firestationService.updateFirestation(firestation);
		firestationService.deleteFirestation(firestation);
	}

	@Test
	void firestationGetByStationNumberTest()
			throws IOException {
		ObjectMapper objectMappper = new ObjectMapper();

		FirestationService firestationService = new FirestationService();
		firestationService.firestationRepository = new FirestationRepository(objectMappper);
		firestationService.medicalRecordRepository = new MedicalRecordRepository(objectMappper);
		firestationService.personRepository = new PersonRepository(objectMappper);


		List<Map<String, String>> retour = firestationService.getByStationNumber("1");

		assertTrue(retour.size() == 7);
		assertTrue(retour.get(6).get(" / nombre d'adulte : ").equals("5"));
		assertTrue(retour.get(6).get(" / nombre d'enfant : ").equals("1"));
	}

	@Test
	void phoneAlertTest() throws IOException {

		ObjectMapper objectMappper = new ObjectMapper();

		FirestationService firestationService = new FirestationService();
		firestationService.firestationRepository = new FirestationRepository(objectMappper);
		firestationService.medicalRecordRepository = new MedicalRecordRepository(objectMappper);
		firestationService.personRepository = new PersonRepository(objectMappper);

		List<String> retour = firestationService.getPersonNumberByFirestationNumber("2");
		assertTrue(retour.size() == 5);
	}

	@Test
	void getStationNumberFromAddress()
			throws IOException {

		ObjectMapper objectMappper = new ObjectMapper();

		FirestationService firestationService = new FirestationService();
		firestationService.firestationRepository = new FirestationRepository(objectMappper);
		firestationService.medicalRecordRepository = new MedicalRecordRepository(objectMappper);
		firestationService.personRepository = new PersonRepository(objectMappper);

		String retour = firestationService.getFirestationNumberByAddress("1509 Culver St");
		assertTrue(retour.equals("3"));

	}

}
