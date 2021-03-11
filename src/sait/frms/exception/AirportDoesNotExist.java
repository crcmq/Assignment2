package sait.frms.exception;

/**
 * If an airport code does not exist, throw this exception
 * @author Mengqiu (Roger) Chen
 *
 */
public class AirportDoesNotExist extends Exception{
	public AirportDoesNotExist() {
		
	}
	
	public AirportDoesNotExist(String message) {
		super (message);
	}
}
