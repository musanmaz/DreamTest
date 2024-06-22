package com.musanmaz.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

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

    public static JsonNode getRequestByName(JsonNode jsonNode, String requestName) {
        return jsonNode.get("requests").get(requestName);
    }

    public static String getStringField(JsonNode jsonNode, String fieldName) {
        return jsonNode.get(fieldName).asText();
    }

    public static Map<String, ?> getObjectField(JsonNode jsonNode, String fieldName) {
        return jsonNode.get(fieldName);
    }
}
