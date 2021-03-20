package sait.frms.problemdomain;

import sait.frms.exception.NullCitizenshipException;
import sait.frms.exception.NullClientNameException;

/**
 * 
 * @author 845368
 *
 */
public class Reservation {
	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;
	
	public Reservation() {
	}
	
	public Reservation (String _code, String _flightCode, String _airline, String _name, String _citizenship, double _cost, boolean _active) {
		this.code = _code;
		this.flightCode = _flightCode;
		this.airline = _airline;
		this.name = _name;
		this.citizenship = _citizenship;
		this.cost = _cost;
		this.active = _active;
	}

	/**
	 * Get reservation code 
	 * @return reservation code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Get flight code
	 * @return flight code
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * Get airline name
	 * @return the name of airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * Get client name
	 * @return client name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get client citizenship
	 * @return client citizenship
	 */
	public String getCitizenship() {
		return citizenship;
	}

	/**
	 * 
	 * @return the cost of the reservation
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * 
	 * @return the status of this reservation: active / inactive
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Change client name of reservation
	 * @param _name
	 * @throws NullClientNameException Thrown if name is empty
	 */
	public void setName(String _name) throws NullClientNameException {
		if (_name.equals("")) {
			throw new NullClientNameException();
		}
		this.name = _name;
	}
	
	/**
	 * Change client citizenship of reservation
	 * @param _citizenship
	 * @throws NullCitizenshipException Thrown if citizenship is empty
	 */
	public void setCitizenship (String _citizenship) throws NullCitizenshipException {
		if (_citizenship.equals("")) {
			throw new NullCitizenshipException();
		}
		this.citizenship = _citizenship;
	}
	
	/**
	 * Change status of reservation
	 * @param _active
	 */
	public void setActive (boolean _active) {
		this.active = _active;
	}
	
	public String toString () {
		String s = String.format("%s, Flight: %s, Airline: %s, Name: %s, Citizenship: %s, Cost: %.2f Active: %b", 
								this.code, this.flightCode, this.airline, this.name, this.citizenship, this.cost, this.active);
		return s;
	}
}
