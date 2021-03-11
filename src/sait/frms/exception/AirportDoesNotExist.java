package sait.frms.exception;

/**
 * If an airport code does not exist, throw this exception
 * @author Mengqiu (Roger) Chen
 *
 */
public class AirportDoesNotExist extends Exception{
	private String message = "Airport does not exist";
	public AirportDoesNotExist() {
		
	}
	
	public AirportDoesNotExist(String _message) {
		super(_message);
	}
	
	public String toString() {
		return this.message;
	}
}
