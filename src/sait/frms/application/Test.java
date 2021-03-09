package sait.frms.application;
import sait.frms.problemdomain.*;
/**
 * This is a test class. Should be deleted after the project is completed.
 * @author Roger
 *
 */
public class Test {

	public static void main(String[] args) {
		
		Flight f = new Flight("CA-1234", "YYC", "YVR", "Monday", "17:00", 200, 100);
		System.out.println(f.toString());
		System.out.println(f.getAirline());
		System.out.println(f.isDomestic());
		
		Reservation r = new Reservation("D1234", "CA-1234", "CONN AIR", "Roger", "Canada", 100, true);
		System.out.println(r.toString());
	}

}
