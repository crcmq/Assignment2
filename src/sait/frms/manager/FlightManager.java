package sait.frms.manager;
import sait.frms.exception.*;
import sait.frms.problemdomain.*;
import java.util.*;
import java.io.*;

public class FlightManager {
	public static final String WEEKDAY_ANY = "Any";
	public static final String WEEKDAY_SUNDAY = "Sunday";
	public static final String WEEKDAY_MONDAY = "Monday";
	public static final String WEEKDAY_TUESDAY = "Tuesday";
	public static final String WEEKDAY_WEDNESDAY = "Wednesday";
	public static final String WEEKDAY_THURSDAY = "Thursday";
	public static final String WEEKDAY_FRIDAY = "Friday";
	public static final String WEEKDAY_SATURDAY = "Saturday";
	
	private ArrayList<Flight> flights = new ArrayList<>();
	private ArrayList<String> airports = new ArrayList<>();
	private ArrayList<String> airportNames = new ArrayList<>();
	
	public FlightManager() {
		populateFlights();
		populateAirports();
	}
	
	public ArrayList<String> getAirports() {
		return this.airports;
	}
	
	public ArrayList<Flight> getFlights () {
		return this.flights;
	}
	
	/**
	 * Find out the name of airport according to the code
	 * @param code code of airport
	 * @return airportName
	 * @throws AirportDoesNotExist if can't find the airport according to the given code.
	 */
	public String findAirportByCode(String code) throws AirportDoesNotExist {
		
		String airportName="";
		for (int i = 0; airportName.isEmpty() && i < airports.size(); i ++) {
			if (airports.get(i).equals(code)) {
				airportName = airportNames.get(i);
			}
		}
		if (airportName.isEmpty()) {
			throw new AirportDoesNotExist ("Cannot find the airport code");
		}
		return airportName;
	}
	
	/**
	 * Find out the flight according to the given code.
	 * @param code
	 * @return result The flight found according to the code
	 * @throws FlightDoesNotExist
	 */
	public Flight findFlightByCode(String code) throws FlightDoesNotExist {
		Flight result = new Flight();
		for (int i = 0; i < flights.size(); i ++) {
			Flight f = flights.get(i);
			String flightCode = f.getCode();
			if (flightCode.equals(code)) {
				result = f;
			}
		}
		if (result.getCode() == null) {
			throw new FlightDoesNotExist ("Flight is not found");
		}
		return result;
	}
	
	/**
	 * Check all the flights to see if there is a flight matching the condition. If so, add it to the list
	 * @param from
	 * @param to
	 * @param weekday
	 * @return flights List of flights matching condition
	 */
	public ArrayList<Flight> findFlights(String from, String to, String weekday) {
		ArrayList<Flight> flights = new ArrayList<>();
		for (int i = 0; i < flights.size(); i ++) {
			Flight f = flights.get(i);
			String flightFrom = f.getFrom();
			String flightTo = f.getTo();
			String flightWeekday = f.getWeekday();
			if (flightFrom.equals(from) && flightTo.equals(to) && flightWeekday.equals(weekday)) {
				flights.add(f);
			}
		}
		return flights;
	}
	
	/**
	 * Add flights from csv file
	 * @throws EmptyFlightException 
	 */
	private void populateFlights() {
		File file = new File ("res\\flights.csv");
		try {
			Scanner in = new Scanner(file);
			// read lines from csv file
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] info = line.split(",");
				String flightcode = info[0];
				String from = info[1];
				String to = info[2];
				String day = info[3];
				String time = info[4];
				int seats = Integer.parseInt(info[5]);
				double cost = Double.parseDouble(info[6]);
							
				Flight f = new Flight(flightcode, from, to, day, time, seats, cost);
				flights.add(f);	
			}
			in.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("flights.csv does not exist");
		}
		
	}
	
	/**
	 * Add airports to the list
	 */
	private void populateAirports() {

		File file = new File("res\\airports.csv");
		try {
			Scanner in = new Scanner (file);
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] airport = line.split(",");
				// list of airport code and list of airport names is synced. 
				airports.add(airport[0]);
				airportNames.add(airport[1]);
			}
		} 
		catch (FileNotFoundException e) {
			System.out.println("airports.csv does not exist");
		}
		
	}
}
