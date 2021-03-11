package sait.frms.exception;

/**
 * If reservation code is invalid, throw this exception
 * @author Mengqiu (Roger) Chen
 *
 */
public class InvalidReservationCodeException extends Exception{
	private String message = "Invalid Reservation Code";
	public InvalidReservationCodeException () {
	}
	public InvalidReservationCodeException (String message) {
		super(message);
	}
	
	public String toString () {
		return this.message;
	}
}
