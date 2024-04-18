package com.openclassrooms.wawa;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.controller.DemoApplication;
import com.controller.SafetyNetController;
import com.database.model.Firestation;
import com.database.model.MedicalRecord;
import com.database.model.Person;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.FirestationRepository;
import com.repository.MedicalRecordRepository;
import com.repository.PersonRepository;
import com.service.FirestationService;
import com.service.MedicalRecordService;
import com.service.PersonService;

@SpringBootTest(classes = DemoApplication.class)
class DemoApplicationTests {

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
			throws JsonParseException, JsonMappingException, IOException {
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
	void phoneAlertTest() throws JsonParseException, JsonMappingException, IOException {

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
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMappper = new ObjectMapper();

		FirestationService firestationService = new FirestationService();
		firestationService.firestationRepository = new FirestationRepository(objectMappper);
		firestationService.medicalRecordRepository = new MedicalRecordRepository(objectMappper);
		firestationService.personRepository = new PersonRepository(objectMappper);

		String retour = firestationService.getFirestationNumberByAddress("1509 Culver St");
		assertTrue(retour.equals("3"));

	}



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
	public void childAlertTest() throws JsonParseException, JsonMappingException, IOException {
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
	void fireTest() throws JsonParseException, JsonMappingException, IOException {
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
	void floodTest() throws JsonParseException, JsonMappingException, IOException {
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
	void getPersonalInfoTest() throws JsonParseException, JsonMappingException, IOException {
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
	void cityTest() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMappper = new ObjectMapper();

		PersonService personService = new PersonService();
		personService.firestationRepository = new FirestationRepository(objectMappper);
		personService.medicalRecordRepository = new MedicalRecordRepository(objectMappper);
		personService.personRepository = new PersonRepository(objectMappper);

		List<String> retour = personService.getAllEmailFromCity("Culver");
		assertTrue(retour.size() == 23);

	}
	
}
