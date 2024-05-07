package com.util;

import java.io.File;
import java.io.IOException;


import com.database.DataBase;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

    private final ObjectMapper objectMapper;

    public JsonReader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SuppressWarnings("unchecked")
    public Object readJsonData(final TypeReference type, final String nodeName) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/data.json"));
        return (Object) objectMapper.readValue(jsonNode.get(nodeName).toString(), type);
    }

    public DataBase getDB() throws IOException {
        final JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/data.json"));
        return objectMapper.readValue(jsonNode.toString(), new TypeReference<DataBase>() {});
    }

    public void updateDB(DataBase db) throws IOException {
        objectMapper.writeValue(new File("src/main/resources/data.json"), db);
    }

}
