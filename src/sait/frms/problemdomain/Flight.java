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
	
	public Flight(String _code, String _from, String _to, String _day, String _time, int _seats, double _cost) {
		try {
			parseCode(_code);
		}
		catch (InvalidFlightCodeException e) {		
		}
		
		this.from = _from;
		this.to = _to;
		this.weekday = _day;
		this.time = _time;
		this.seats = _seats;
		this.costPerSeat = _cost;
		
	}

	public String getCode() {
		return code;
	}

	public String getAirline() {
		return airlineName;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getWeekday() {
		return weekday;
	}

	public String getTime() {
		return time;
	}

	public int getSeats() {
		return seats;
	}

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
		
		String pattern = "([A-Z][A-Z]-)\\d{4}";
		boolean valid = code.matches(pattern);
		if (!valid) {
			throw new InvalidFlightCodeException ("Invalid flight code");
		}
	}
	
	public String toString() {
		//TODO
		return "";
	}
}
