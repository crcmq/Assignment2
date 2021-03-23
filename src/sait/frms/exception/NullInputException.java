package sait.frms.exception;

/**
 * If the user did not input any value, throw this exception
 * @author Mengqiu (Roger) Chen, Ebele Egenti, AJ Russell De Leon, Dmitriy Fominykh
 *
 */
public class NullInputException extends Exception{
	private String message = "The input value is null";
	
	public NullInputException() {
		
	}
	
	public NullInputException (String _message) {
		super(_message);
	}
	
}
