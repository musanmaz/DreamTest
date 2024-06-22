package com.musanmaz.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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

    public static JsonNode getObjectField(JsonNode jsonNode, String fieldName) {
        JsonNode field = jsonNode.get(fieldName);
        if (field == null || field.isNull()) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.createObjectNode();
        }
        return field;
    }

    public static Map<String, String> convertJsonToMap(JsonNode jsonNode) {
        Map<String, String> map = new HashMap<>();
        if (jsonNode != null && jsonNode.isObject()) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                map.put(field.getKey(), field.getValue().asText());
            }
        }
        return map;
    }
}
