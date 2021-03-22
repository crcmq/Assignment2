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
	
	public Flight(String _code, String _from, String _to, String _day, String _time, int _seats, double _cost) throws InvalidFlightCodeException {
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
			throw e;
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
	 * Book a seat for this flight
	 * @param _seat
	 * @throws SeatUnavailableException If book a seat from a flight with 0 seat.
	 */
	public void bookSeat() throws SeatUnavailableException {
		if (this.seats > 0) {
			this.seats --;
		}
		else {
			throw new SeatUnavailableException();
		}
	}
	
	/**
	 * Canceling a booking will increase the number of available seats.
	 */
	public void cancelSeat() {
		this.seats ++;
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
		// check the first character of from and to location. if both are 'Y', the flight is domestic
		isDomestic = this.from.charAt(0) == 'Y' && this.to.charAt(0) == 'Y';
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
		
		String s = String.format("%s, From: %s, To: %s, Day: %s, Time: %s, Cost: %.2f, Seats: %d",
								this.code, this.from, this.to, this.weekday, this.time, this.costPerSeat, this.seats);
		return s;
	}
}
