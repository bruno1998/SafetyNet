package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.database.model.Firestation;
import com.service.FirestationService;

@RestController
public class FirestationController {

	@Autowired
	private FirestationService firestationService;

	private static final Logger logger = LoggerFactory.getLogger(FirestationController.class);

	@GetMapping("/phoneAlert")
	@ResponseBody
	public List<String> getListOfPhoneNumberNearFirestation(@RequestParam("firestation") String firestation_number){
		logger.info("get the list of phone number from people covered by the firestation number "+ firestation_number);
		List<String> retour = null ;
		try {retour = firestationService.getPersonNumberByFirestationNumber(firestation_number);
		}catch (IOException e) {
			logger.error(e.toString());
		}
				
		logger.info(retour.toString());
		return retour;
	}
	
	
	@GetMapping("/firestation")
	@ResponseBody
	@Transactional
	public List<Map<String, String>> getFirestationByNumber(@RequestParam("stationNumber") String stationNumber)
			{
		logger.info("get the list of people covered by the firestation number : " + stationNumber);
		List<Map<String, String>> retour = null;
		try {retour = firestationService.getByStationNumber(stationNumber);
		}catch (NumberFormatException|IOException e) {
			logger.error(e.toString());
		}
		logger.info(retour.toString());
		return retour;
	}
	

	@PostMapping("/firestation")
	public ResponseEntity<Firestation> createFirestation(@RequestBody Firestation firestation){
		logger.info("create the firestation " + firestation.getStation() + " / "+ firestation.getAddress());
		try {firestationService.createFirestation(firestation);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(firestation.toString());
		return ResponseEntity.ok(firestation);

	}

	@PutMapping("/firestation")
	public ResponseEntity<Firestation> updateFirestation(@RequestBody Firestation firestation){
		logger.info("update the firestation for the address" + firestation.getAddress());
		try {firestationService.updateFirestation(firestation);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(firestation.toString());
		return ResponseEntity.ok(firestation);
	}

	@DeleteMapping("/firestation")
	public ResponseEntity<Firestation> deleteFirestation(@RequestHeader("station") String station, @RequestHeader("address") String address){
		logger.info("delete the firestation station : " + station + " / " + "address : " + address);
		Firestation fs = new Firestation();
		fs.setAddress(address);
		fs.setStation(station);
		try {firestationService.deleteFirestation(fs);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		return ResponseEntity.ok(fs);
	}



}
