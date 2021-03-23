package sait.frms.exception;

/**
 * This exception is thrown if client name is empty
 * @author Mengqiu (Roger) Chen, Ebele Egenti, AJ Russell De Leon, Dmitriy Fominykh
 *
 */
public class NullClientNameException extends Exception {
	private String message = "Client name cannot be empty";
	
	public NullClientNameException () {		
	}
	
	public NullClientNameException (String _message) {
		super(_message);
	}
}
