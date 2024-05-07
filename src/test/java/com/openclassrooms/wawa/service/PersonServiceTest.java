package com.openclassrooms.wawa.service;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.controller.DemoApplication;
import com.database.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.FirestationRepository;
import com.repository.MedicalRecordRepository;
import com.repository.PersonRepository;
import com.service.PersonService;

@SpringBootTest(classes = DemoApplication.class)
public class PersonServiceTest {


	@Test
	void personTest() throws IOException {
		ObjectMapper objectMappper = new ObjectMapper();

		PersonService personService = new PersonService();
		personService.firestationRepository = new FirestationRepository(objectMappper);
		personService.medicalRecordRepository = new MedicalRecordRepository(objectMappper);
		personService.personRepository = new PersonRepository(objectMappper);

		Person pe = new Person();
		pe.setFirstName("bruno");
		pe.setLastName("mazel");
		pe.setAddress("25 rue du chateau");
		pe.setCity("blagnac");
		pe.setPhone("0612345678");;

		personService.createPerson(pe);
		pe.setPhone("0000000000");
		personService.updatePerson(pe);
		personService.deletePerson(pe);
	}

	@Test
	public void childAlertTest() throws IOException {
		ObjectMapper objectMappper = new ObjectMapper();

		PersonService personService = new PersonService();
		personService.firestationRepository = new FirestationRepository(objectMappper);
		personService.medicalRecordRepository = new MedicalRecordRepository(objectMappper);
		personService.personRepository = new PersonRepository(objectMappper);

		List<Map<String, String>> retour = personService.getListPersonByAddress("29 15th St");
		assertNull(retour);
		retour = personService.getListPersonByAddress("1509 Culver St");
		assertTrue(retour.size() == 5);
	}

	@Test
	void fireTest() throws IOException {
		ObjectMapper objectMappper = new ObjectMapper();

		PersonService personService = new PersonService();
		personService.firestationRepository = new FirestationRepository(objectMappper);
		personService.medicalRecordRepository = new MedicalRecordRepository(objectMappper);
		personService.personRepository = new PersonRepository(objectMappper);

		List<Map<String, Object>> retour =
				personService.getListPersonByAddressWithMedicalRecord("1509 Culver St");
		assertTrue(retour.size() == 5);
	}

	@Test
	void floodTest() throws IOException {
		ObjectMapper objectMappper = new ObjectMapper();

		PersonService personService = new PersonService();
		personService.firestationRepository = new FirestationRepository(objectMappper);
		personService.medicalRecordRepository = new MedicalRecordRepository(objectMappper);
		personService.personRepository = new PersonRepository(objectMappper);
		List<String> entre = new ArrayList<String>();
		entre.add("1");
		Map<String, Object> retour =
				personService.getListOfPeopleSortedByAddressCoveredByThoseStations(entre);
		assertTrue(retour.keySet().size() == 3);
	}

	@Test
	void getPersonalInfoTest() throws IOException {
		ObjectMapper objectMappper = new ObjectMapper();

		PersonService personService = new PersonService();
		personService.firestationRepository = new FirestationRepository(objectMappper);
		personService.medicalRecordRepository = new MedicalRecordRepository(objectMappper);
		personService.personRepository = new PersonRepository(objectMappper);

		List<Map<String, Object>> retour =
				personService.getPersonnalInformationOfPeople("John", "Boyd");

		String fn = (String) retour.get(0).get("fistname");
		String ln = (String) retour.get(0).get("lastname");
		assertTrue(fn.equals("John"));
		assertTrue(ln.equals("Boyd"));
	}

	@Test
	void cityTest() throws IOException {
		ObjectMapper objectMappper = new ObjectMapper();

		PersonService personService = new PersonService();
		personService.firestationRepository = new FirestationRepository(objectMappper);
		personService.medicalRecordRepository = new MedicalRecordRepository(objectMappper);
		personService.personRepository = new PersonRepository(objectMappper);

		List<String> retour = personService.getAllEmailFromCity("Culver");
		assertTrue(retour.size() == 23);

	}

}
