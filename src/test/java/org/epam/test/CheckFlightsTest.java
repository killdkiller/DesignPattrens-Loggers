package org.epam.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.epam.driver.BaseTest;
import org.epam.driver.SetUpSelenium;
import org.epam.model.FlightDetails;
import org.epam.model.PassengerDetails;
import org.epam.utils.DataUtil;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckFlightsTest extends BaseTest{
	
	private static final String PAGE_TITLE = "Official Wizz Air website | Book direct for the cheapest prices";
	private static final String ORIGIN_COUNTRY = "Belgium";
	private static final String ORIGIN_AIRPORT = "Brussels Charleroi";
	private static final String WRONG_DESTINATION_COUNTRY = "Germany";
	private static final String DESTINATION_COUNTRY = "Hungary";
	private static final String DESTINATION_AIRPORT = "Budapest";
	private static final String DYNAIRPORTSEARCH ="//*[starts-with(@class,'flight-search__panel__locations-container')]//*[contains(text(),'%s')]";
	private static final String NO_RESULT_COMMENTS = "There is no result for this search.";
	private static final String EXP_PASSENGER_DATA = "1 adult 2 children";
	private static final String FLIGHT_RESULT_PAGE = "Select flights";
	private static final int I_NUM_OF_CHILD = 2;	
	private static int iMaxRandomDays = 180;
	
	
	private Date departureDate = DataUtil.giveRandomDate(iMaxRandomDays);
	private Date returnDate = null;
	private PassengerDetails passengerData = new PassengerDetails()
													.withChild(I_NUM_OF_CHILD);
	private FlightDetails flightData = new FlightDetails()
									   .withOrignCountry(ORIGIN_COUNTRY)
									   .withOrignAirport(ORIGIN_AIRPORT)
									   .withDestinationCountry(DESTINATION_COUNTRY)
									   .withDestinationAirport(DESTINATION_AIRPORT)
									   .withDepartureDate(departureDate)
									   .withPassengerDetails(passengerData);

	@Test
	public void validateFlightHomaePageTest()
	{
		String appTitle = SetUpSelenium.getDriver().getTitle();
		Assert.assertEquals(PAGE_TITLE, appTitle,"Flight Home Page Title is not matching ");
		logger.info("Title is Matching");
	}
	
	@Test(dependsOnMethods={"validateFlightHomaePageTest"})
	public void selectOriginAirportTest()
	{
		objFlightSearchPage.setOrignAirport(flightData.getOrigin_Country(),flightData.getOrigin_Airport());
		String appOriginAirport = objFlightSearchPage.getOriginAttribute("value");
		Assert.assertEquals(flightData.getOrigin_Airport(), appOriginAirport,"Origin Airport value is not entered properly ");
		logger.info("OriginAirport Value is selected and validated");
	}
	
	@Test(dependsOnMethods={"selectOriginAirportTest"})
	public void selectDestinationAirportTest()
	{
		objFlightSearchPage.setDestinationCountry(WRONG_DESTINATION_COUNTRY);
		boolean isDisplayed = SetUpSelenium.getDriver().findElement(By.xpath(String.format(DYNAIRPORTSEARCH, NO_RESULT_COMMENTS))).isDisplayed();
		Assert.assertTrue(isDisplayed, "Empty Search search result is not displayed");
		logger.info("Wrong Destination validated");
		
		objFlightSearchPage.setDestinationAirport(flightData.getDestination_Country(),flightData.getDestination_Airport());
		String appDestinationAirport = objFlightSearchPage.getDestinationAttribute("value");
		Assert.assertEquals(flightData.getDestination_Airport(), appDestinationAirport,"Destinatio Airport value is not entered properly ");
		logger.info("DestinationAirport Value is selected and validated");
	}
	
	@Test(dependsOnMethods={"selectDestinationAirportTest"})
	public void selectDepartureAndReturnDate()
	{
		
		logger.info("DepartureDate :"+flightData.getDeparture_Date());
		objFlightSearchPage.setDeparturDate(flightData.getDeparture_Date());
		logger.info("Departure Date Value is selected sucessfully");
		
		int iNumOfDaysOfTrip =3;
		Calendar cal = Calendar.getInstance();
		cal.setTime(departureDate);		 		 		 
		cal.add(Calendar.DAY_OF_MONTH, iNumOfDaysOfTrip);
		returnDate = cal.getTime();
		logger.info("ReturnDate: "+returnDate);
		flightData.withReturnDate(returnDate);
		objFlightSearchPage.setReturnDate(flightData.getReturn_Date());
		logger.info("Return Date Value is selected sucessfully");
		
	}
	
	
	@Test(dependsOnMethods={"selectDepartureAndReturnDate"})
	public void selectPassengers()
	{
		objFlightSearchPage.setPassengersData(flightData.getPassengerDetails().getChild());
		String appPassenger = objFlightSearchPage.getPassengerData();
		Assert.assertEquals(EXP_PASSENGER_DATA, appPassenger,"Passengers data is not filled properly");
		logger.info("Passenger values are selected and validated sucessfully");
	}
	
	@Test(dependsOnMethods={"selectPassengers"})
	public void validateSearchResults()
	{
		objFlightResultsPage = objFlightSearchPage.clickSearch();
		Assert.assertTrue(objFlightResultsPage.isTextPresentInFlightResultsPage(FLIGHT_RESULT_PAGE),"Flight Results Page is not prsent");
		logger.info("validated Flights results page");
		
		String appDepartureDate = objFlightResultsPage.getDepartureDate();
		String appReturnDate = objFlightResultsPage.getReturnDate();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE dd, MMM");
		String strDepartureDate = sdf.format(flightData.getDeparture_Date());
		Assert.assertEquals(strDepartureDate.toLowerCase(), appDepartureDate.toLowerCase(),"Departure date is not entered properly");
		String strReturnDate = sdf.format(flightData.getReturn_Date());
		Assert.assertEquals(strReturnDate.toLowerCase(), appReturnDate.toLowerCase(),"Return date is not entered properly");
		logger.info("validated Flights results Dates");
	}
	
	@Test(dependsOnMethods={"validateSearchResults"})
	public void printFlightDetails()
	{
		objFlightResultsPage.printFlightDepartureDetails();
		logger.info("printed Flight Departure Details");
	}
	

}
