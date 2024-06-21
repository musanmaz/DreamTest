package com.musanmaz.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtils {

    public static JsonNode loadJsonFile(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }
}
