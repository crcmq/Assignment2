package sait.frms.exception;

/**
 * This exception is thrown if citizenship is empty
 * @author Mengqiu (Roger) Chen, Ebele Egenti, AJ Russell De Leon, Dmitriy Fominykh
 *
 */
public class NullCitizenshipException extends Exception {
	private String message = "Citizenship cannot be empty";
	
	public NullCitizenshipException() {
	}
	
	public NullCitizenshipException (String _message) {
		super(_message);
	}
}
