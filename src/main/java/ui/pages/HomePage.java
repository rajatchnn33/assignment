package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
    @FindBy(xpath = "//a[text()='LIVE TV']")
    private WebElement liveTV;
    @FindBy(xpath="//a[text()='WEATHER']")
    private WebElement weatherLink;
    private WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver=driver;
        driver.get("https://www.ndtv.com/");
        PageFactory.initElements(driver,this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public WeatherPage clickOnWeather()
    {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", weatherLink);
        return new WeatherPage(driver);

    }
}
