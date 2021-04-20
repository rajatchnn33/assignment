package com.ndtv.sanity.tests;


import api.WeatherApi;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.ws.rs.core.Response;
import model.WeatherFromApi;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import ui.driver.BrowserType;
import ui.driver.DriverManager;
import ui.driver.DriverManagerFactory;
import org.openqa.selenium.WebDriver;
import ui.pages.HomePage;
import ui.pages.WeatherPage;
import utils.TemperatureVariationException;
import utils.TestUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;


public class WeatherPageTest extends TestUtils {
    private WebDriver driver;
    private DriverManager driverManager;

    @BeforeTest
    public void beforeTest() {
        driverManager = DriverManagerFactory.getManager(BrowserType.CHROME);

    }

    @BeforeMethod
    public void beforeMethod() {
        driver = driverManager.getWebDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @DataProvider(name = "testData")
    public Iterator<Object[]> testData() throws IOException
    {
        return readDataFile();
    }
    @Test(dataProvider = "testData")
    public void testWeatherTemperatureFunctionality(String city, String variationInCelsius, String variationInFahrenheit) throws IOException, TemperatureVariationException {
        HomePage homePage=new HomePage(driver);
        WeatherPage weatherPage=homePage.clickOnWeather();
        weatherPage.typeCityInSearchBox(city);
        Assertions.assertThat(weatherPage.IsCityTemperatureInfoPresentOnMap(city)).as("Temperature Info is present when we search and select city in serach Box") .isTrue();
        weatherPage.clickOnCityOnMap(city);
        Assertions.assertThat(weatherPage.isTemperaturePopUpDisplayed()).as("Temperature Pop Up  is present when any city is clicked on map") .isTrue();
        Assertions.assertThat(weatherPage.isCorrectCityDisplayed(city)).as("Pop up should be displayed for the clicked city only") .isTrue();
        HashMap<String,String> weatherObjectFromUi= weatherPage.getWeatherObjectFromUi(city);
        WeatherApi weatherApi=new WeatherApi();
        Response response= weatherApi.getWeatherDetailsByCityInCelsius(city);
        Assertions.assertThat(response.getStatus()).as("Response Code should be 200").isEqualTo(200);
        WeatherFromApi weatherObjectFromApi= parseResponse(response, new TypeReference<>() {});
        Assertions.assertThat(compareTemperatureFromUiAndApi(weatherObjectFromUi,weatherObjectFromApi, variationInCelsius)).as("Temperature diffrence should fall in allowed variation").isTrue();

    }

    @AfterMethod
    public void afterMethod() {
        driverManager.quitDriver();
    }
}
