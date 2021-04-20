package ui.driver;

import org.openqa.selenium.WebDriver;

public  abstract class DriverManager {
    public WebDriver driver;
    public abstract void initilaizeBrowser();
    public abstract void createDriver();
    public abstract void closeBrowser();
    public void quitDriver()
    {
        if(driver!=null)
        {
            closeBrowser();
            driver=null;
        }

    }


    public WebDriver getWebDriver() {
        if(driver==null)
        {
            initilaizeBrowser();
            createDriver();
        }
        return driver;

    }
}
