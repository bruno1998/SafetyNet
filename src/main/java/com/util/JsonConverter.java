package com.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
	
    public String toJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
