package org.epam.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.Logger;

public class FlightResultsPage extends BasePage {
	private Logger logger = LogManager.getRootLogger();
	
	private static final String DYN_DEPFLIGHT_TIMINGS = "(//div[@id='fare-selector-outbound']//tbody[@class='booking-flow__prices-table__content']//td[contains(@class,'prices-table__content__column--time')])[%d]";	

	@FindBy(xpath = "//div[@id='fare-selector-outbound']//label[contains(@class,'flight-select__chart__day__label--selected')]//time")
	private WebElement departureLabel;
	
	@FindBy(xpath = "//div[@id='fare-selector-return']//label[contains(@class,'flight-select__chart__day__label--selected')]//time")
	private WebElement returnLabel;
	
	@FindBys({
		@FindBy(xpath = "//div[@id='fare-selector-outbound']//tbody[@class='booking-flow__prices-table__content']")
	})
	private List<WebElement> departureFlightSDetails;

	
	public FlightResultsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	public boolean isTextPresentInFlightResultsPage(String strText)
	{
		waitForElementVisible(departureLabel, 30);
		return isTextPresent(strText);
	}
	
	public String getDepartureDate()
	{
		logger.info("getting Departure Date");
		return departureLabel.getText().trim();
	}
	
	public String getReturnDate()
	{
		logger.info("getting return Date");
		return returnLabel.getText().trim();
	}
	
	public void printFlightDepartureDetails()
	{
		int NumofDepartureFlights = departureFlightSDetails.size();
		System.out.println("Num Of Departure Flights:"+NumofDepartureFlights);
		for(int i=1;i<=NumofDepartureFlights;i++)
		{
			System.out.print("Departure Flight"+i+" :");
			System.out.println(driver.findElement(By.xpath(String.format(DYN_DEPFLIGHT_TIMINGS, i))).getText().trim());
			
		}
	}
	
}
