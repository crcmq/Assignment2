package sait.frms.application;
import java.util.ArrayList;

import sait.frms.exception.EmptyFlightException;
import sait.frms.problemdomain.*;
/**
 * This is a test class. Should be deleted after the project is completed.
 * @author Roger
 *
 */
public class Test {

	public static void main(String[] args) throws EmptyFlightException {
		ArrayList<Flight> flights = new ArrayList<>();
		try {
			Flight f = new Flight("CA-1234", "YYC", "YVR", "Monday", "17:00", 200, 100);
			flights.add(f);
		}
		catch(EmptyFlightException e) {
			
		}
		
		System.out.println(flights.get(0).toString());
		
		
	}

}
