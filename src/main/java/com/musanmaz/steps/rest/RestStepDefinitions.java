package com.musanmaz.steps.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.musanmaz.config.ConfigurationLoader;
import com.musanmaz.utils.JsonUtils;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class RestStepDefinitions {

    private Response response;

    @Given("I call the {string} request")
    public void callRest(String requestName) {
        JsonNode allRequests = JsonUtils.loadJsonFile("src/main/resources/rest/requests.json");
        JsonNode requestConfig = JsonUtils.getRequestByName(allRequests, requestName);

        String baseUrl = ConfigurationLoader.getRestBaseUrl();
        String endpoint = JsonUtils.getStringField(requestConfig, "endpoint");
        String method = JsonUtils.getStringField(requestConfig, "method");

        switch (method.toUpperCase()) {
            case "GET":
                response = given().params(JsonUtils.getObjectField(requestConfig, "params")).when().get(baseUrl + endpoint);
                break;
            case "POST":
                response = given().body(JsonUtils.getObjectField(requestConfig, "body")).when().post(baseUrl + endpoint);
                break;
            case "PUT":
                response = given().body(JsonUtils.getObjectField(requestConfig, "body")).when().put(baseUrl + endpoint);
                break;
            case "DELETE":
                response = given().body(JsonUtils.getObjectField(requestConfig, "body")).when().delete(baseUrl + endpoint);
                break;
            default:
                throw new IllegalArgumentException("Unsupported method: " + method);
        }
    }

    @Then("I see status code {int}")
    public void seeStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("I see response {string} field with value {string}")
    public void seeResponseFieldWithValue(String fieldName, String value) {
        response.then().assertThat().body(fieldName, equalTo(value));
    }
}