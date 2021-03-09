package sait.frms.exception;

public class InvalidReservationCodeException extends Exception{
	public InvalidReservationCodeException () {
	}
	public InvalidReservationCodeException (String message) {
		super(message);
	}
}
