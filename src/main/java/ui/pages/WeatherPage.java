package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class WeatherPage {
    private WebDriver driver;
    public WeatherPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
    @FindBy(xpath = "//*[@id='searchBox']")
    private WebElement searchBox;

    public void typeCity(String city)
    {//*[@id='Ajmer']
        searchBox.sendKeys(city);
        String checkBoxXpath = "//*[@id='xxx']";
        driver.findElement(By.xpath(checkBoxXpath.replace("xxx",city))).click();

    }





}
