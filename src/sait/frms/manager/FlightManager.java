package sait.frms.manager;
import sait.frms.problemdomain.*;
import java.util.*;

public class FlightManager {
	public final String WEEKDAY_ANY = "Any";
	public final String WEEKDAY_SUNDAY = "Sunday";
	public final String WEEKDAY_MONDAY = "Monday";
	public final String WEEKDAY_TUESDAY = "Tuesday";
	public final String WEEKDAY_WEDNESDAY = "Wednesday";
	public final String WEEKDAY_THURSDAY = "Thursday";
	public final String WEEKDAY_FRIDAY = "Friday";
	public final String WEEKDAY_SATURDAY = "Saturday";
	
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
