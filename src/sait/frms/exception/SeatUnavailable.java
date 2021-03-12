package sait.frms.exception;

/**
 * If the seat of fligt is 0, throw this exception
 * @author Mengqiu (Roger) Chen
 *
 */
public class SeatUnavailable extends Exception{
	private String message = "There is no available seat";
	
	public SeatUnavailable() {
		
	}
	
	public SeatUnavailable(String message) {
		super(message);
	}
}
