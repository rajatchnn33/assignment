package com.ndtv.snaity.tests;


import driver.BrowserType;
import driver.DriverManager;
import driver.DriverManagerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.WeatherPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class WeatherPageTest {
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
    @Test
    public void testWeatherTemperature()
    {
        HomePage homePage=new HomePage(driver);
        homePage.clickOnWeather();



    }

    @AfterMethod
    public void afterMethod() {
        driverManager.quitDriver();
    }
}
