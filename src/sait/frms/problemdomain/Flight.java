package sait.frms.problemdomain;

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
		//TODO
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
	
	public boolean isDomestic() {
		//TODO
		return true;
	}
	
	private void parseCode(String code) {
		//TODO
	}
	
	public String toString() {
		//TODO
		return "";
	}
}
