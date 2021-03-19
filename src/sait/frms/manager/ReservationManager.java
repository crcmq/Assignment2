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
	
	/**
	 * Generate random reservation code according to flight
	 * D is for domestic, I is for international
	 * @param flight
	 * @return reservation code of the reservation
	 */
	private String generateReservationCode(Flight flight) {
		// generate random number from 1000 to 9999
		String reservationCode = "";
		int randomCode = (int)(Math.random() * 9000 + 1000);
		if (flight.isDomestic()) {
			reservationCode = "D" + randomCode;
		}
		else {
			reservationCode = "I" + randomCode;
		}
		return reservationCode;
	}
	
	private void populateFromBinary() {
		//TODO
	}
}
