package sait.frms.exception;

/**
 * If flight has null value for its attribute, throw this exception
 * @author Mengqiu (Roger) Chen
 *
 */
public class EmptyFlightException extends Exception{
	public EmptyFlightException() {	
	}
	
	public EmptyFlightException(String message) {
		super(message);
	}
}
