package exceptions;

public class SSWriteResponseException extends RuntimeException {

    private static final long serialVersionUID = -1374558192397255486L;

    public SSWriteResponseException() {
	super();
	
    }

    public SSWriteResponseException(String message, Throwable cause) {
	super(message, cause);

    }

    public SSWriteResponseException(String message) {
	super(message);

    }

    public SSWriteResponseException(Throwable cause) {
	super(cause);

    }
    
}
