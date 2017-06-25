package org.epam.driver;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.pages.FlightResultsPage;
import org.epam.pages.FlightSearchPage;
import org.epam.utils.CustomListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners(CustomListener.class)
public class BaseTest {
	
	protected FlightSearchPage objFlightSearchPage;
	protected FlightResultsPage objFlightResultsPage;
	protected Logger logger = LogManager.getRootLogger();
	@BeforeClass
	@Parameters({"browserName","url"})
	public void setUp(String browserName,String url)
	{
		logger.info("initializing the driver with browser name:"+browserName);
		SetUpSelenium.initializeDriver(browserName);
		SetUpSelenium.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		SetUpSelenium.getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		logger.info("navigating to the Url:"+url);
		SetUpSelenium.goToUrl(url);
		objFlightSearchPage = new FlightSearchPage(SetUpSelenium.getDriver());
	}
	
	@AfterClass
	public void closeDriver()
	{
		logger.info("closing the driver");
		SetUpSelenium.tearUp();
		logger.info("driver is close");
	}

}
