package exceptions;

public class SSDBException extends RuntimeException {
    private static final long serialVersionUID = 938363834179499319L;

    public SSDBException() {
	super();
	
    }

    public SSDBException(String message, Throwable cause) {
	super(message, cause);
	
    }

    public SSDBException(String message) {
	super(message);
	
    }

    public SSDBException(Throwable cause) {
	super(cause);

    }
    
}
