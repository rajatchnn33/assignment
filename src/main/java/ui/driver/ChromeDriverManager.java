package ui.driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class ChromeDriverManager extends DriverManager {
    private ChromeDriverService chromeDriverService;
    @Override
    public void initilaizeBrowser() {
        if(chromeDriverService==null)
        {
            try
            {
                chromeDriverService=new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                chromeDriverService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void createDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options=new ChromeOptions();
        options.merge(capabilities);
        driver =new ChromeDriver(chromeDriverService,options);


    }

    @Override
    public void closeBrowser() {
        if (chromeDriverService!=null && chromeDriverService.isRunning())
            chromeDriverService.stop();

    }
}
