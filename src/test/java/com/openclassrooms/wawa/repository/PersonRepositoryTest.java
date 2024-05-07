package com.openclassrooms.wawa.repository;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.controller.DemoApplication;
import com.controller.PersonController;
import com.database.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.PersonRepository;
import com.service.PersonService;

@SpringBootTest(classes = DemoApplication.class)
public class PersonRepositoryTest {
	
	
    private int port = 8085;
	private String url ="http://localhost:8085";
	
	@Test
	public void createPersonTest() throws IOException, InterruptedException {
		

		PersonController ctrl = new PersonController();
		
		PersonService ps = new PersonService();
		ObjectMapper objectMappper = new ObjectMapper();
		PersonRepository pr = new PersonRepository(objectMappper);
		ps.personRepository=pr;
		ctrl.personService=ps;
		
    	Person pe = new Person();
    	pe.setFirstName("bruno");
    	pe.setLastName("mazel");
    	pe.setAddress("25 rue du chateau");
    	pe.setCity("blagnac");
    	pe.setPhone("0612345678");
    	ctrl.createPerson(pe);
    	   	
    	pe.setPhone("0000000000");
    	ctrl.updatePerson(pe);
   	
        ctrl.deletePreson(pe.getFirstName(), pe.getLastName());
	}

}
