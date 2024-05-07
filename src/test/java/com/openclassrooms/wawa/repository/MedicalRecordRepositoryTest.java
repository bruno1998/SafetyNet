package com.openclassrooms.wawa.repository;


import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.controller.DemoApplication;
import com.controller.MedicalRecordController;
import com.database.model.MedicalRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.MedicalRecordRepository;
import com.service.MedicalRecordService;

@SpringBootTest(classes = DemoApplication.class)
public class MedicalRecordRepositoryTest {
	
	
    private int port = 8085;
	private String url ="http://localhost:8085";
    
    @Test
    public void testApiEndpointMedicalRecord() throws IOException, InterruptedException {

    	
		MedicalRecordController ctrl = new MedicalRecordController();
		
		MedicalRecordService ps = new MedicalRecordService();
		ObjectMapper objectMappper = new ObjectMapper();
		MedicalRecordRepository pr = new MedicalRecordRepository(objectMappper);
		ps.medicalRecordRepository=pr;
		ctrl.medicalRecordService=ps;
    	
    	MedicalRecord mr = new MedicalRecord();
    	mr.setFirstName("bruno");
    	mr.setLastName("mazel");
    	mr.setBirthdate("01/01/2021");
    	mr.setAllergies(new ArrayList<String>());
    	mr.setMedications(new ArrayList<String>());
    	ctrl.createMedicalRecord(mr);
    	   	
    	mr.setBirthdate("10/10/2022");
    	ctrl.updateMedicalRecord(mr);
   	
        ctrl.deleteMedicalRecord(mr.getFirstName(), mr.getLastName());
    }

}
