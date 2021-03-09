package sait.frms.exception;

/**
 * If flight code is invalid, throw this excception
 * @author Mengqiu (Roger) Chen
 *
 */
public class InvalidFlightCodeException extends Exception{
	public InvalidFlightCodeException() {
	}
	
	public InvalidFlightCodeException(String message) {
		super(message);
	}
}
