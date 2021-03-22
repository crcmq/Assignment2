package sait.frms.exception;

/**
 * If the seat of fligt is 0, throw this exception
 * @author Mengqiu (Roger) Chen
 *
 */
public class SeatUnavailableException extends Exception{
	private String message = "There is no available seat";
	
	public SeatUnavailableException() {
		
	}
	
	public SeatUnavailableException(String message) {
		super(message);
	}
}
