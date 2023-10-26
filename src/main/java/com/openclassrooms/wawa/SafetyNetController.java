package com.openclassrooms.wawa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.websocket.server.PathParam;

@Controller
public class SafetyNetController {

	@Autowired
	private FirestationService firestationService;
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/hello")
	@ResponseBody
	public String sayHello() {
		return "hello";
	}
	
	@GetMapping("/firestation")
	@ResponseBody
	@Transactional
	public List<Map<String, String>> getFirestationByNumber(@RequestParam("stationNumber") String stationNumber) throws JsonParseException, JsonMappingException, NumberFormatException, IOException {
		return null;
//		return  firestationService.getByStationNumber(stationNumber);
	}
	
	@GetMapping("childAlert")
	@ResponseBody
	public ArrayList<String> getListChildMissing(@RequestParam("address") String address) throws JsonParseException, JsonMappingException, IOException {
		return personService.getListPersonByAddress(address);
	}
	
	@GetMapping("phoneAlert")
	@ResponseBody
	public List<String> getListOfPhoneNumberNearFirestation(@RequestParam("firestation") String firestation_number) throws JsonParseException, JsonMappingException, IOException {
		return firestationService.getPersonNumberByFirestationNumber(firestation_number);
	}
		
	@GetMapping("fire")
	@ResponseBody
	public Object getPeopleAndFirestationByAddress(@RequestParam("address") String address) throws JsonParseException, JsonMappingException, IOException {
//		List<String> merde = personService.getListPersonByAddress(address);
//		merde.add(firestationService.getFirestationNumberByAddress(address));
//		return merde;
		return null;
	}
	
	@GetMapping("flood")
	@ResponseBody
	public String getListOfPeopleAndMedicalRecordByStation(@RequestParam("stations") String stations) {
		return stations;
	}
	
	@GetMapping("personalInfo")
	@ResponseBody
	public String getListOfPeopleAndMedicalRecordByFirstAndLastName(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname) {
		return lastname;
	}
	
	@GetMapping("communityEmail")
	@ResponseBody
	public String getListOfEmailOfAllPeopleByCity(@RequestParam("city") String city) {
		return city;
	}

}
