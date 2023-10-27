package com.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.database.DataBase;
import com.database.model.MedicalRecord;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader{
	
    private final ObjectMapper objectMapper;

    public JsonReader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Object readJsonData(TypeReference type, String nodeName) throws JsonParseException, JsonMappingException, IOException{

        JsonNode jsonNode = objectMapper.readTree(new File("C:/Users/Bruno/eclipse-workspace-openclassrooms/SafetyNet_Alerts/src/main/java/com/openclassrooms/wawa/data.json"));
        return (Object) objectMapper.readValue(jsonNode.get(nodeName).toString(), type );
    }
    
    public DataBase getDB() throws IOException {
    	JsonNode jsonNode = objectMapper.readTree(new File("C:/Users/Bruno/eclipse-workspace-openclassrooms/SafetyNet_Alerts/src/main/java/com/openclassrooms/wawa/data.json"));
    	System.out.println(jsonNode.get("medicalrecords").toString());
    	DataBase db = objectMapper.readValue(jsonNode.toString(), DataBase.class);
//    	db.setMedicalRecords((List<MedicalRecord>) objectMapper.readValue(jsonNode.get("medicalrecords").toString(), new TypeReference<List<MedicalRecord>>() {}));
    	return db; 
    }
    
    public void updateDB(DataBase db) throws JsonGenerationException, JsonMappingException, IOException {
    	System.out.println(db.getPersons().size());
    	System.out.println(db.getFirestations().size());
    	System.out.println(db.getMedicalRecords().size()+"bonjour");
    	objectMapper.writeValue(new File("src/main/java/com/openclassrooms/wawa/data.json"), db);
    }
    
    
    
    
    
    
}