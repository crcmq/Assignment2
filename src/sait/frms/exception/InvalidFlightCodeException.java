package sait.frms.exception;

public class InvalidFlightCodeException extends Exception{
	public InvalidFlightCodeException() {
	}
	
	public InvalidFlightCodeException(String message) {
		super(message);
	}
}
