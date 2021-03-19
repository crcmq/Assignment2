package sait.frms.manager;
import sait.frms.problemdomain.*;
import java.util.*;

public class ReservationManager {
	private ArrayList<Reservation> reservations;
	
	public ReservationManager() {
		//TODO
	}
	
	public Reservation makeReservation(Flight flight, String name, String citizenship) {
		//TODO
		return null;
	}
	
	public ArrayList<Reservation> findReservation(String code, String airline, String name) {
		//TODO
		return null;
	}
	
	public Reservation findReservationByCode(String code) {
		//TODO
		return null;
	}
	
	public void persist() {
		//TODO
	}
	
	/**
	 * Get available seats for each flight	
	 * @param flight
	 * @return the number of available seats
	 */
	private int getAvailableSeats(Flight flight) {
		int seats = flight.getSeats();
		return seats;
	}
	
	private String generateReservationCode(Flight flight) {
		//TODO
		return "";
	}
	
	private void populateFromBinary() {
		//TODO
	}
}
