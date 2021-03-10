package sait.frms.problemdomain;
import java.util.*;

import sait.frms.exception.*;
public class Flight {
	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;
	
	public Flight() {
	}
	
	public Flight(String _code, String _from, String _to, String _day, String _time, int _seats, double _cost) throws EmptyFlightException {
		try {
			parseCode(_code);
			this.from = _from;
			this.to = _to;
			this.weekday = _day;
			this.time = _time;
			this.seats = _seats;
			this.costPerSeat = _cost;
		}
		catch (InvalidFlightCodeException e) {	
			// if the flightcode is invalid, the flight object should be null
			throw new EmptyFlightException();
		}
	}
	/**
	 * Get flight code
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Get Flight airline Name
	 * @return airlineName
	 */
	public String getAirline() {
		return airlineName;
	}

	/**
	 * Get From location of flight
	 * @return from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Get To loaction of flight
	 * @return to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Get weekday of flight
	 * @return weekday
	 */
	public String getWeekday() {
		return weekday;
	}

	/**
	 * Get time of flight
	 * @return time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Get the number of seats flight
	 * @return seats
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * Get the cost per seat
	 * @return costPerSeat
	 */
	public double getCostPerSeat() {
		return costPerSeat;
	}
	
	/**
	 * Check whether from and to attribute is domestic. Both of them should be domestic if a flight is domestic
	 * @return isDomestic
	 */
	public boolean isDomestic() {
		
		boolean isDomestic = false;
		String[] domesticAirport = {"YYC", "YEG", "YUL", "YOW", "YYZ", "YVR", "YWG"};
		isDomestic = Arrays.asList(domesticAirport).contains(this.from) && 
				Arrays.asList(domesticAirport).contains(this.to);
		return isDomestic;
	}
	
	/**
	 * Validate flight code. 
	 * @param code
	 * @throws InvalidFlightCodeException
	 */
	private void parseCode(String code) throws InvalidFlightCodeException {
		
		// check flight code format first
		String pattern = "([A-Z][A-Z]-)\\d{4}";
		boolean valid = code.matches(pattern);
		if (!valid) {
			throw new InvalidFlightCodeException ("Invalid flight code");
		}
		else {
			this.code = code;
			
			// read airline name
			String airlineCode = code.substring(0,2);
			String airlineName = "";
			switch (airlineCode) {
				case "OA": airlineName = "Otto Airlines"; break;
				case "CA": airlineName = "Conned Air"; break;
				case "TB": airlineName = "Try a Bus Airways"; break;
				case "VA": airlineName = "Vertical Airways"; break;
			}
			this.airlineName = airlineName;	
		}
		
	}
	
	public String toString() {
		
		String s = String.format("%7s, From: %3s, To: %3s, Day: %-9s, Time: %5s, Cost: %4.2f", 
								this.code, this.from, this.to, this.weekday, this.time, this.costPerSeat);
		return s;
	}
}
