package com.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.database.model.MedicalRecord;
import com.service.MedicalRecordService;

@RestController
public class MedicalRecordController {

	@Autowired
	public MedicalRecordService medicalRecordService;

	private static final Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);


	@PostMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord){
		logger.info("create the medicalRecord for " + medicalRecord.getLastName() + " / "+ medicalRecord.getFirstName());
		try {medicalRecordService.createMedicalRecord(medicalRecord);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(medicalRecord.toString());
		return ResponseEntity.ok(medicalRecord);
	}

	@PutMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord){
		logger.info("update the medicalRecord for " + medicalRecord.getLastName() + " / "+ medicalRecord.getFirstName());
		try {medicalRecordService.updateMedicalRecord(medicalRecord);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		logger.info(medicalRecord.toString());
		return ResponseEntity.ok(medicalRecord);
	}

	@DeleteMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> deleteMedicalRecord(@RequestHeader("firstName") String firstName, @RequestHeader("lastName") String lastName){
		logger.info("delete the medicalRecord firstname : " + firstName + " / lastname : " + lastName);
		MedicalRecord medicalRecord = null;
		try {medicalRecord = medicalRecordService.getByStationNumber(firstName, lastName);
			medicalRecordService.deleteMedicalRecord(medicalRecord);
		}catch (IOException e) {
			logger.error(e.toString());
		}
		return ResponseEntity.ok(medicalRecord);
	}
}
