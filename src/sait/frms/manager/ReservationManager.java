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
	
	private int getAvailableSeats(Flight flight) {
		//TODO
		return 0;
	}
	
	private String generateReservationCode(Flight flight) {
		//TODO
		return "";
	}
	
	private void populateFromBinary() {
		//TODO
	}
}
