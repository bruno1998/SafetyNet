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
import com.controller.SafetyNetController;
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
		

		SafetyNetController ctrl = new SafetyNetController();
		ctrl.sayHello();
		
		PersonService ps = new PersonService();
		ObjectMapper objectMappper = new ObjectMapper();
		PersonRepository pr = new PersonRepository(objectMappper);
		ps.personRepository=pr;
		ctrl.personService=ps;
		
    	HttpClient httpClient = HttpClient.newHttpClient();
    	Person pe = new Person();
    	pe.setFirstName("bruno");
    	pe.setLastName("mazel");
    	pe.setAddress("25 rue du chateau");
    	pe.setCity("blagnac");
    	pe.setPhone("0612345678");
    	ctrl.createPerson(pe);
//    	HttpRequest request= HttpRequest.newBuilder()
//                .uri(URI.create(url+"/person"))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(pe.toJson().toString())).build();
//                
//    	HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        
//    	assertEquals(response.statusCode(),200);
//    	assertEquals(response.body(),pe.toJson().toString());
    	   	
    	pe.setPhone("0000000000");
    	ctrl.updatePerson(pe);
//    	request= HttpRequest.newBuilder()
//                .uri(URI.create(url+"/person"))
//                .header("Content-Type", "application/json")
//                .PUT(HttpRequest.BodyPublishers.ofString(pe.toJson().toString())).build();
//    	
//    	System.out.println("/////////////////////////////");
//    	response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//		System.out.println("/////////////////////////////");
//		assertEquals(response.statusCode(),200);
//    	assertEquals(response.body(),pe.toJson().toString());
   	
        ctrl.deletePreson(pe.getFirstName(), pe.getLastName());
//    	request= HttpRequest.newBuilder()
//                .uri(URI.create(url+"/person"))
//                .header("firstName", "bruno")
//                .header("lastName", "mazel")
//                .header("Content-Type", "application/json")
//                .DELETE().build();
//    	
//		response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        assertEquals(response.statusCode(),200);
	}

}
