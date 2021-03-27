package sait.frms.manager;
import sait.frms.exception.NullCitizenshipException;
import sait.frms.exception.NullClientNameException;
import sait.frms.exception.SeatUnavailableException;
import sait.frms.problemdomain.*;

import java.io.*;
import java.util.*;

/**
 * Reservation manager to manage all the reservations from binary file
 * The data is I/O from "res/reservations.bin"
 * The database is using logic delete, which means that inactive reservations will be kept in the database
 * @author Mengqiu (Roger) Chen, Ebele Egenti, AJ Russell De Leon, Dmitriy Fominykh
 *
 */
public class ReservationManager {
	
	private ArrayList<Reservation> reservations = new ArrayList<>();
	private RandomAccessFile raf;
	
	/**
	 * Constructor loading data from binary file
	 * @throws IOException
	 */
	public ReservationManager() throws IOException {
		populateFromBinary();
	}
	
	/**
	 * Make a reservation according to the booked flight and user information
	 * If the flight is unavailable, the reservation won't happen
	 * This method will check empty name and citizenship
	 * @param flight Flight object
	 * @param name Client name
	 * @param citizenship Client citizenship
	 * @return reservation object
	 * @throws NullClientNameException Thrown if client name is empty
	 * @throws NullCitizenshipException Thrown if client citizenship is empty
	 * @throws SeatUnavailableException Thrown if no more seat available
	 */
	public Reservation makeReservation(Flight flight, String name, String citizenship) throws NullClientNameException, NullCitizenshipException, SeatUnavailableException {
		if (name.equals("")){
			throw new NullClientNameException();
		}
		if (citizenship.equals("")) {
			throw new NullCitizenshipException();
		}
		try {
			flight.bookSeat();
			String reservationCode = generateReservationCode(flight);
			String flightCode = flight.getCode();
			String airline = flight.getAirline();
			double cost = flight.getCostPerSeat();
			Reservation r = new Reservation (reservationCode, flightCode, airline, name, citizenship, cost, true);
			reservations.add(r);
			persist();
			return r;
		}
		catch (SeatUnavailableException s) {
			// The system will do nothing. The reservation will not be made
			throw s;
		}
		catch (IOException e) {
			
		}
		return null;
	}
	
	/**
	 * Update reservation according to user input
	 * @param r Reservation
	 * @param _name Client name
	 * @param _citizenship Client citizenship
	 * @param status Is it active?
	 * @throws NullClientNameException Thrown if client name is empty
	 * @throws NullCitizenshipException Thrown if citizenship is empty
	 */
	public void updateReservation (Reservation r, String _name, String _citizenship, boolean status) throws NullClientNameException, NullCitizenshipException {
		if (_name.equals("")){
			throw new NullClientNameException();
		}
		if (_citizenship.equals("")) {
			throw new NullCitizenshipException();
		}

		try {
					
			r.setActive(status);
			r.setName(_name);
			r.setCitizenship(_citizenship);
			
			// write data to binary file
			persist();
			
		}
		catch (IOException e) {
			
		}
	}
	
	/**
	 * Search through all the reservations and find out matching record 
	 * then add them to an arraylist
	 * @param _code Reservation code
	 * @param _airline Airline name
	 * @param _name Client name
	 * @return foundReservations Arraylist of found reservations
	 */
	public ArrayList<Reservation> findReservation(String _code, String _airline, String _name) {
		ArrayList<Reservation> foundReservations = new ArrayList<>();
		for (Reservation r : reservations) {
				if (r.isActive()) {
				String code = r.getCode();
				String airline = r.getAirline();
				String name = r.getName();
				boolean matched = false;
				// the reservation should match all the conditions that are not empty
				matched = _code.equals("") || _code.equals(code);
				matched = matched && (_airline.equals("") || _airline.equals(airline));
				matched = matched && (_name.equals("") || _name.equals(name));
						
				if (matched) {
					foundReservations.add(r);
			}
			}

		}
		return foundReservations;
	}
	
	/**
	 * Search through reservations and find matching reservation according to the given code
	 * @param code Reservation code
	 * @return reservation if found, null if not found
	 */
	public Reservation findReservationByCode(String code) {
		
		for (Reservation r : reservations) {
			if (r.getCode().equals(code)) {
				return r;
			}
		}
		return null;
	}
	
	/**
	 * Write record from reservations to random access file
	 * The size of reservation object in random access file is 1 + 10 + 10 + 20 + 50 + 30 + 8 = 129 bytes
	 * @throws IOException
	 */
	public void persist() throws IOException {
		
		raf = new RandomAccessFile("res/reservations.bin", "rw");
		for (Reservation r : reservations) {
			raf.writeBoolean(r.isActive()); // 1 byte
			
			String reservationCode = String.format("%-8s", r.getCode()); // 10 byte
			raf.writeUTF(reservationCode);
			
			String flightCode = String.format("%-8s", r.getFlightCode()); // 10 byte
			raf.writeUTF(flightCode);
			
			String airline = String.format("%-18s", r.getAirline()); // 20 byte
			raf.writeUTF(airline);
			
			String name = String.format("%-48s", r.getName()); // 50 byte
			raf.writeUTF(name);
			
			String citizenship = String.format("%-28s", r.getCitizenship()); // 30 byte
			raf.writeUTF(citizenship);
			
			raf.writeDouble(r.getCost()); // 8 byte
		}
		
	}
	
	/**
	 * Get available seats for each flight	
	 * @param flight Flight object
	 * @return the number of available seats
	 */
	private int getAvailableSeats(Flight flight) {
		int seats = flight.getSeats();
		return seats;
	}
	
	/**
	 * Generate random reservation code according to flight
	 * D is for domestic, I is for international
	 * @param flight Flight object
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
	
	/**
	 * read reservations from binary file
	 * @throws IOException
	 */
	private void populateFromBinary() throws IOException {
		raf = new RandomAccessFile("res/reservations.bin", "rw");
		for (long position = 0; position < raf.length(); position += 129) {
			raf.seek(position);
			boolean isActive = raf.readBoolean();
			String reservationCode = raf.readUTF().trim();
			String flightCode = raf.readUTF().trim();
			String airline = raf.readUTF().trim();
			String name = raf.readUTF().trim();
			String citizenship = raf.readUTF().trim();
			double cost = raf.readDouble();
			
			Reservation r = new Reservation (reservationCode, flightCode, airline, name, citizenship, cost, isActive);
			reservations.add(r);
		}
	}
}
