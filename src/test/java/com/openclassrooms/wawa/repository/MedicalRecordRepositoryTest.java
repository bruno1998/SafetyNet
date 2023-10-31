package com.openclassrooms.wawa.repository;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.database.model.Firestation;
import com.database.model.MedicalRecord;

public class MedicalRecordRepositoryTest {
	
	
    private int port = 8085;
	private String url ="http://localhost:8085";
    
    @Test
    public void testApiEndpointMedicalRecord() throws IOException, InterruptedException {

    	HttpClient httpClient = HttpClient.newHttpClient();
    	MedicalRecord mr = new MedicalRecord();
    	mr.setFirstName("bruno");
    	mr.setLastName("mazel");
    	mr.setBirthdate("01/01/2021");
    	mr.setAllergies(new ArrayList<String>());
    	mr.setMedications(new ArrayList<String>());
    	HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(url+"/medicalRecord"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mr.toJson().toString())).build();
                
    	HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
    	assertEquals(response.statusCode(),200);
    	assertEquals(response.body(),mr.toJson().toString());
    	   	
    	mr.setBirthdate("10/10/2022");
    	request= HttpRequest.newBuilder()
                .uri(URI.create(url+"/medicalRecord"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(mr.toJson().toString())).build();
    	
    	System.out.println("/////////////////////////////");
		response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println("/////////////////////////////");
		assertEquals(response.statusCode(),200);
    	assertEquals(response.body(),mr.toJson().toString());
   	
        
    	request= HttpRequest.newBuilder()
                .uri(URI.create(url+"/medicalRecord"))
                .header("firstName", "bruno")
                .header("lastName", "mazel")
                .header("Content-Type", "application/json")
                .DELETE().build();
    	
		response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(response.statusCode(),200);
    }

}
