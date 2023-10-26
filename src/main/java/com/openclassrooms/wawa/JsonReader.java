package com.openclassrooms.wawa;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
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

        JsonNode jsonNode = objectMapper.readTree(new File("C:/Users/Bruno/eclipse-workspace-openclassrooms/demo/src/main/java/com/openclassrooms/wawa/data.json"));

        return (Object) objectMapper.readValue(jsonNode.get(nodeName).toString(), type );
    }
    

}