package driver;

public class DriverManagerFactory {
    public DriverManager driverManager;
    public DriverManager getManager(BrowserType browserType)
    {
     switch(browserType)
     {
         case CHROME:
             driverManager=new ChromeDriverManager();
             break;
         case FIREFOX:
            // driverManager =new FirefoxDriverManager();
             break;
         default:
             driverManager=new ChromeDriverManager();
             break;


     }
  return driverManager;
    }
}
