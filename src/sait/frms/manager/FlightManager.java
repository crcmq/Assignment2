package sait.frms.manager;
import sait.frms.exception.*;
import sait.frms.problemdomain.*;
import java.util.*;
import java.io.*;

/**
 * Flight manager to manage all the flights
 * The data of flights come from "res/flights.csv"
 * The data of airports come from "res/airports.csv"
 *  
 * @author Mengqiu (Roger) Chen, Ebele Egenti, AJ Russell De Leon, Dmitriy Fominykh
 *
 */
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
	
	/**
	 * Constructor loading all the flights and airports
	 * @throws FileNotFoundException Thrown if flights.csv or airports.csv not found
	 */
	public FlightManager() throws FileNotFoundException {
		populateFlights();
		populateAirports();
	}
	
	/**
	 * Get list of airports
	 * @return airports ArrayList of airports
	 */
	public ArrayList<String> getAirports() {
		return this.airports;
	}
	
	/**
	 * Get list of flights
	 * @return flights ArrayList of flights
	 */
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
	 * Find out the flight according to the given flight code.
	 * 
	 * @param code Flight code
	 * @return result The flight found according to the code
	 * 
	 */
	public Flight findFlightByCode(String code) {
		Flight result = new Flight();
		for (int i = 0; i < flights.size(); i ++) {
			Flight f = flights.get(i);
			String flightCode = f.getCode();
			if (flightCode.equals(code)) {
				result = f;
			}
		}
		return result;
	}
	
	/**
	 * Check all the flights to see if there is a flight matching the condition. If so, add it to the list of found flights
	 * @param from From airport
	 * @param to To airport
	 * @param weekday Weekday of the flight
	 * @return flights List of flights matching condition
	 */
	public ArrayList<Flight> findFlights(String from, String to, String weekday) {
		ArrayList<Flight> foundFlights = new ArrayList<>();
		for (int i = 0; i < flights.size(); i ++) {
			Flight f = flights.get(i);
			String flightFrom = f.getFrom();
			String flightTo = f.getTo();
			String flightWeekday = f.getWeekday();
			
			// from and to must match. If user chose any, flights of any day will be added. Otherwise, weekday must match		
			if (flightFrom.equals(from) && flightTo.equals(to) && (flightWeekday.equals(weekday) || weekday.equals(WEEKDAY_ANY))) {
				foundFlights.add(f);
			}
		}
		return foundFlights;
	}
	
	/**
	 * Add flights from csv file
	 * @throws FileNotFoundException Thrown if flights.csv not found
	 */
	private void populateFlights() throws FileNotFoundException {
		File file = new File ("res\\flights.csv");
		
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
			// invalid flights will be skipped	
			try {
				Flight f = new Flight(flightcode, from, to, day, time, seats, cost);
			
				flights.add(f);	
			}
			catch (InvalidFlightCodeException e) {
				// skip adding this flight
			}
		}
		in.close();
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
			in.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("airports.csv does not exist");
		}
	}
}
