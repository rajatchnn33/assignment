package ui.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class WeatherPage {
    private WebDriver driver;
    public WeatherPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }
    @FindBy(xpath = "//*[@id='searchBox']")
    private WebElement searchBox;
    @FindBy(xpath="//div[@class='leaflet-popup-content-wrapper']")
    private WebElement temperatureInfo;
    @FindBy(xpath="*//div[@class='leaflet-popup-content']//span[@class='heading']/b")
    private List<WebElement> weatherInfo;



    public void typeCityInSearchBox(String city)
    {//*[@id='Ajmer']
        searchBox.sendKeys(city);
        String checkBoxXpath = "//*[@id='xxx']";
        driver.findElement(By.xpath(checkBoxXpath.replace("xxx",city))).click();
    }
    public boolean IsCityTemperatureInfoPresentOnMap(String city)
    {
        String temperaturePattern="(^[-+]?[0-9]+\\\\.[0-9]+℃$)|(^[-+]?[0-9]+℃$)|(^[-+]?[0-9]+\\\\.[0-9]+℉$)|(^[-+]?[0-9]+℉$)";
        boolean isPresent;
        StringBuilder cityContainerXpath=new StringBuilder("//div[@class='outerContainer' and @title='CITY']");
        WebElement cityContainer=driver.findElement(By.xpath(cityContainerXpath.toString().replace("CITY",city)));
        if(cityContainer.isDisplayed())
        {

            List<WebElement> temperatureInfo=driver.findElements(By.xpath(cityContainerXpath.append("/div[@class='temperatureContainer']/span").toString().replace("CITY",city)));
            isPresent=temperatureInfo.stream().noneMatch(span -> span.getAttribute("innerHTML").isEmpty());

        }
        else throw new RuntimeException("City Container is not displayed even after clicking on the city to be searched");

       return isPresent;
    }

 public void clickOnCityOnMap(String city)
 {
     String cityOnMapXpath= "*//div[@class='cityText' and text()='CITY']";
     WebElement cityOnMap=driver.findElement(By.xpath(cityOnMapXpath.replace("CITY",city)));
     cityOnMap.click();
 }

 public boolean  isTemperaturePopUpDisplayed()
 {
     return temperatureInfo.isDisplayed();

 }

 public boolean isCorrectCityDisplayed(String city)
 {
     String xpath= "*//div[@class='leaflet-popup-content']//span[contains(text(),'CITY')]";
     WebElement citySpan=driver.findElement(By.xpath(xpath.replace("CITY",city)));
     return citySpan.isDisplayed();

 }

 public HashMap<String,String> getWeatherObjectFromUi(String city)
 {
     HashMap<String,String> weatherMap=new LinkedHashMap<>();
     weatherMap.put("city",city);
     weatherInfo.forEach(info->
     {
        String weatherDetail= info.getAttribute("innerHTML");
        String [] detail=weatherDetail.split(":");
         weatherMap.put(detail[0],detail[1]);
     });
     return weatherMap;
 }




}
