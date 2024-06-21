package com.musanmaz.pages;

import com.fasterxml.jackson.databind.JsonNode;
import com.musanmaz.utils.JsonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected WebDriver driver;
    protected JsonNode elements;
    protected String urlPath;

    public BasePage(WebDriver driver, String jsonFilePath) {
        this.driver = driver;
        JsonNode jsonNode = JsonUtils.loadJsonFile(jsonFilePath);
        this.urlPath = jsonNode.get("urlPath").asText();
        this.elements = jsonNode.get("elements");
    }

    public void openPage(String baseUrl) {
        driver.get(baseUrl + urlPath);
    }


    protected WebElement findElement(String elementName) {
        JsonNode elementNode = elements.get(elementName);
        String type = elementNode.get("type").asText();
        String value = elementNode.get("value").asText();

        switch (type.toLowerCase()) {
            case "xpath":
                return driver.findElement(By.xpath(value));
            case "css":
                return driver.findElement(By.cssSelector(value));
            case "id":
                return driver.findElement(By.id(value));
            case "name":
                return driver.findElement(By.name(value));
            case "class":
                return driver.findElement(By.className(value));
            default:
                throw new IllegalArgumentException("Unsupported locator type: " + type);
        }
    }

    public void fillElement(String elementName, String value) {
        WebElement element = findElement(elementName);
        element.sendKeys(value);
    }

    public void clickElement(String elementName) {
        WebElement element = findElement(elementName);
        element.click();
    }
}
