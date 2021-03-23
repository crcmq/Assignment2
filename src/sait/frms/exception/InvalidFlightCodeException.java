package sait.frms.exception;

/**
 * If flight code is invalid, throw this exception
 * @author Mengqiu (Roger) Chen, Ebele Egenti, AJ Russell De Leon, Dmitriy Fominykh
 *
 */
public class InvalidFlightCodeException extends Exception{
	private String message = "Invalid flight code";
	public InvalidFlightCodeException() {
	}
	
	public InvalidFlightCodeException(String message) {
		super(message);
	}
	
	public String toString() {
		return this.message;
	}
}
