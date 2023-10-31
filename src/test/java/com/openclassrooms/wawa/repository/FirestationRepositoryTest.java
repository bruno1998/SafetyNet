package com.openclassrooms.wawa.repository;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.database.model.Firestation;

public class FirestationRepositoryTest {

    private int port = 8085;
	private String url ="http://localhost:8085";
    
    @Test
    public void testApiEndpointFirestation() throws IOException, InterruptedException {

    	HttpClient httpClient = HttpClient.newHttpClient();
    	Firestation fs = new Firestation();
    	fs.setAddress("25 rue du chateau");
    	fs.setStation("8");
    	HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(url+"/firestation"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(fs.toJson().toString())).build();
                
    	HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
    	assertEquals(response.statusCode(),200);
    	assertEquals(response.body(),fs.toJson().toString());
    	   	
    	fs.setStation("10");
    	request= HttpRequest.newBuilder()
                .uri(URI.create(url+"/firestation"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(fs.toJson().toString())).build();
    	
		response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    	assertEquals(response.statusCode(),200);
    	assertEquals(response.body(),fs.toJson().toString());
   	
        
    	request= HttpRequest.newBuilder()
                .uri(URI.create(url+"/firestation"))
                .header("address", "25 rue du chateau")
                .header("station", "10")
                .header("Content-Type", "application/json")
                .DELETE().build();
    	
		response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(response.statusCode(),200);
    }
}
