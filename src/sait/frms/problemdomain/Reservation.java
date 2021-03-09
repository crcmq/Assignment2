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
		return "";
	}
}
