package sait.frms.problemdomain;

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

	public String getCode() {
		return code;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public String getAirline() {
		return airline;
	}

	public String getName() {
		return name;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public double getCost() {
		return cost;
	}

	public boolean isActive() {
		return active;
	}
	
	public void setName(String _name) {
		this.name = _name;
	}
	
	public void setCitizenship (String _citizenship) {
		this.citizenship = _citizenship;
	}
	
	public void setActive (boolean _active) {
		this.active = _active;
	}
	
	public String toString () {
		String s = String.format("%s, Flight: %s, Airline: %s, Name: %s, Citizenship: %s, Cost: %.2f Active: %b", 
								this.code, this.flightCode, this.airline, this.name, this.citizenship, this.cost, this.active);
		return s;
	}
}
