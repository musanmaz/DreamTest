package com.musanmaz.steps;

import com.musanmaz.config.ConfigurationLoader;
import com.musanmaz.drivers.DriverFactory;
import com.musanmaz.pages.BasePage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class StepDefinitions {

    private WebDriver driver;
    private BasePage page;

    @Given("I open the {string} page")
    public void iOpenThePage(String pageName) {
        driver = DriverFactory.createDriver(ConfigurationLoader.getBrowser());
        page = new BasePage(driver, "src/main/resources/pages/" + pageName + ".json");
        page.openPage(ConfigurationLoader.getBaseUrl());
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

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
