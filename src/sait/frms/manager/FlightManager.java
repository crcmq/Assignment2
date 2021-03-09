package sait.frms.manager;
import sait.frms.problemdomain.*;
import java.util.*;

public class FlightManager {
	public static final String WEEKDAY_ANY = "Any";
	public static final String WEEKDAY_SUNDAY = "Sunday";
	public static final String WEEKDAY_MONDAY = "Monday";
	public static final String WEEKDAY_TUESDAY = "Tuesday";
	public static final String WEEKDAY_WEDNESDAY = "Wednesday";
	public static final String WEEKDAY_THURSDAY = "Thursday";
	public static final String WEEKDAY_FRIDAY = "Friday";
	public static final String WEEKDAY_SATURDAY = "Saturday";
	
	private ArrayList<Flight> flights;
	private ArrayList<String> airports;
	
	public FlightManager() {
		//TODO
	}
	
	public ArrayList<String> getAirports() {
		return this.airports;
	}
	
	public ArrayList<Flight> getFlights () {
		return this.flights;
	}
	
	public String findAirportByCode(String code) {
		//TODO
		return "";
	}
	
	public Flight findFlightByCode(String code) {
		//TODO
		return null;
	}
	
	public ArrayList<Flight> findFlights(String from, String weekday) {
		//TODO
		return null;
	}
	
	private void populateFlights() {
		//TODO
	}
	
	private void populateAirports() {
		//TODO
	}
}
