package com.musanmaz.steps.web;

import com.musanmaz.config.ConfigurationLoader;
import com.musanmaz.drivers.DriverFactory;
import com.musanmaz.pages.BasePage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class WebStepDefinitions {

    private WebDriver driver;
    private BasePage page;

    @Given("I open the {string} page")
    public void iOpenThePage(String pageName) {
        driver = DriverFactory.createDriver(ConfigurationLoader.getBrowser());
        page = new BasePage(driver, "src/main/resources/pages/" + pageName + ".json");
        page.openPage(ConfigurationLoader.getWebBaseUrl());
    }

    @When("I fill:")
    public void fillDataMap(Map<String, String> dataMap) {
        for (Map.Entry<String, String> item : dataMap.entrySet()) {
            page.fillElement(item.getKey(), item.getValue());
        }
    }

    @When("I click on {string}")
    public void clickOnElement(String elementName) {
        page.clickElement(elementName);
    }

    @And("I see {string} element")
    public void seeElement(String elementName) {
        page.seeElement(elementName);
    }

    @And("I see {string} text")
    public void seeText(String text) {
        page.seeText(text);
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
