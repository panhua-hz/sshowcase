package exceptions;

public class SSServiceCallException extends RuntimeException {
    private static final long serialVersionUID = -4476869858110699849L;

    public SSServiceCallException() {
	super();
	
    }

    public SSServiceCallException(String message, Throwable cause) {
	super(message, cause);
	
    }

    public SSServiceCallException(String message) {
	super(message);

    }

    public SSServiceCallException(Throwable cause) {
	super(cause);

    }
    
}
