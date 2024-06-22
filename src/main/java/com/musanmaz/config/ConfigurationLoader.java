package com.musanmaz.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConfigurationLoader {
    private static JsonNode webConfig;
    private static JsonNode restConfig;

    static {
        ObjectMapper mapper = new ObjectMapper();
        try {
            webConfig = mapper.readTree(new File("src/main/resources/web/config.json"));
            restConfig = mapper.readTree(new File("src/main/resources/rest/config.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getWebBaseUrl() {
        return webConfig.get("baseUrl").asText();
    }

    public static String getRestBaseUrl() {
        return restConfig.get("baseUrl").asText();
    }

    public static String getBrowser() {
        return webConfig.get("browser").asText(); // Assuming browser config is only for web tests
    }

    public static int getTimeout() {
        return webConfig.get("timeout").asInt(); // Assuming timeout config is only for web tests
    }
}
