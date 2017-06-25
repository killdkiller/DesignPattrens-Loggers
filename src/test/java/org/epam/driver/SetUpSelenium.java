package org.epam.driver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SetUpSelenium {
	
	private static WebDriver driver ;
	public static final int IMINTIME = 10;
	public static final int IMAXTIME = 30;
	public static final int IMIDTIME = 20;
	private static Logger logger = LogManager.getRootLogger();
	
	public static void initializeDriver(String browserName)
	{
		if(null == driver)
		{
			switch(Browser.valueOf(browserName.toUpperCase()))
			{
			   case FIREFOX:
				   System.setProperty("webdriver.gecko.driver", "resources\\geckodriver.exe");
//					driver = new DecoratedDriver(new FirefoxDriver());
				   driver = new FirefoxDriver();
				   logger.info("driver is i?ntialized with gecko driver");
					break;
			   case CHROME:
				   System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
//					driver = new DecoratedDriver(new ChromeDriver());
					driver = new ChromeDriver();
					 logger.info("driver is intialized with chrome driver");
					break;
			   case IE:
				   System.setProperty("webdriver.ie.driver", "resources\\IEDriverServer32Bit.exe");
//					driver =  new DecoratedDriver(new InternetExplorerDriver());
					driver = new InternetExplorerDriver();
					 logger.info("driver is intialized with IE driver");
					break;
				default:
					System.setProperty("webdriver.gecko.driver", "resources\\geckodriver.exe");
//					driver = new DecoratedDriver(new FirefoxDriver());
					driver = new FirefoxDriver();
					 logger.info("intializing browser with browserName :"+browserName+", is not defined in our code, so driver is intialized with gecko driver  ");
					break;
					
			}
		}
		
	}
	
	public static WebDriver getDriver()
	{
		return driver;
	}
	
	public static void tearUp()
	{
		if(null!=driver)
		{
			driver.close();
			driver.quit();
			driver = null;
		}
	}
	
	public static void goToUrl(String url)
	{
		driver.get(url);
		logger.info("Navigated to the url:"+url);
		driver.manage().window().maximize();
		logger.info("Maximized the browser");
	}
	public enum Browser{
		FIREFOX,CHROME,IE
	}

}


