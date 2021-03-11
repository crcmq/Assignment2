package sait.frms.exception;

/**
 * If cannot find flight according to the given information, throw this exception
 * @author Mengqiu (Roger) Chen
 *
 */
public class FlightDoesNotExist extends Exception{
	private String message = "Flight does not exist";
	
	public FlightDoesNotExist() {
		
	}
	
	public FlightDoesNotExist(String _message) {
		super(_message);
	}
	
	public String toString() {
		return this.message;
	}
}
