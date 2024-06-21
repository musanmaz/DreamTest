package com.musanmaz.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConfigurationLoader {
    private static JsonNode config;

    static {
        ObjectMapper mapper = new ObjectMapper();
        try {
            config = mapper.readTree(new File("src/main/resources/config/config.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseUrl() {
        return config.get("baseUrl").asText();
    }

    public static String getBrowser() {
        return config.get("browser").asText();
    }

    public static int getTimeout() {
        return config.get("timeout").asInt();
    }
}
