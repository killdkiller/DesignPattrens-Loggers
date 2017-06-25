package org.epam.model;

import java.util.Date;

public class FlightDetails {
	private String origin_Country ;
	private String origin_Airport ;
	private String destination_Country ;
	private String destination_Airport ;
	private Date departure_Date;
	private Date return_Date;
	private PassengerDetails passengerDetails;
	
	public FlightDetails()
	{
		passengerDetails = new PassengerDetails();
	}
	
	public FlightDetails withOrignCountry(String orignCountry)
	{
		this.setOrigin_Country(orignCountry);
		return this;
	}
	
	public FlightDetails withOrignAirport(String orignAirport)
	{
		this.setOrigin_Airport(orignAirport);
		return this;
	}
	
	public FlightDetails withDestinationCountry(String destinationCountry)
	{
		this.setDestination_Country(destinationCountry);
		return this;
	}
	
	public FlightDetails withDestinationAirport(String destinationAirport)
	{
		this.setDestination_Airport(destinationAirport);
		return this;
	}
	

	public FlightDetails withDepartureDate(Date depDate)
	{
		this.setDeparture_Date(depDate);
		return this;
	}
	
	public FlightDetails withReturnDate(Date returnDate)
	{
		this.setReturn_Date(returnDate);
		return this;
	}
	
	public FlightDetails withPassengerDetails(PassengerDetails passengerDetails)
	{
		this.setPassengerDetails(passengerDetails);
		return this;
	}
	
	public String getOrigin_Country() {
		return origin_Country;
	}



	public void setOrigin_Country(String origin_Country) {
		this.origin_Country = origin_Country;
	}



	public String getOrigin_Airport() {
		return origin_Airport;
	}



	public void setOrigin_Airport(String origin_Airport) {
		this.origin_Airport = origin_Airport;
	}



	public String getDestination_Country() {
		return destination_Country;
	}



	public void setDestination_Country(String destination_Country) {
		this.destination_Country = destination_Country;
	}



	public String getDestination_Airport() {
		return destination_Airport;
	}



	public void setDestination_Airport(String destination_Airport) {
		this.destination_Airport = destination_Airport;
	}



	public PassengerDetails getPassengerDetails() {
		return passengerDetails;
	}



	public void setPassengerDetails(PassengerDetails passengerDetails) {
		this.passengerDetails = passengerDetails;
	}
	
	public Date getDeparture_Date() {
		return departure_Date;
	}

	public void setDeparture_Date(Date departure_Date) {
		this.departure_Date = departure_Date;
	}

	public Date getReturn_Date() {
		return return_Date;
	}

	public void setReturn_Date(Date return_Date) {
		this.return_Date = return_Date;
	}


}
