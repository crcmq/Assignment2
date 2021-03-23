package sait.frms.exception;

/**
 * If the seat of flight is 0, and user still wants to book this flight, then throw this exception
 * @author Mengqiu (Roger) Chen, Ebele Egenti, AJ Russell De Leon, Dmitriy Fominykh
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
