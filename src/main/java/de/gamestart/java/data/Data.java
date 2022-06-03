package de.gamestart.java.data;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Data {
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(this);
        } catch (Exception e) {
            System.err.println("Failed to convert Data to JSON!");
        }

        return json;
    }
}
