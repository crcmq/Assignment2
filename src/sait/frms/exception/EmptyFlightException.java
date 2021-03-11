package sait.frms.exception;

/**
 * If flight has null value for its attribute, throw this exception
 * @author Mengqiu (Roger) Chen
 *
 */
public class EmptyFlightException extends Exception{
	private String message = "The flight is invalid";
	public EmptyFlightException() {	
	}
	
	public EmptyFlightException(String _message) {
		super(_message);
	}
	
	public String toString() {
		return this.message;
	}
}
