package exceptions;

public class SSUnSupportException extends RuntimeException {
	private static final long serialVersionUID = -2971891497974889028L;

	public SSUnSupportException() {

	}

	public SSUnSupportException(String message) {
		super(message);
	}

	public SSUnSupportException(Throwable t) {
		super(t);
	}
	public SSUnSupportException(String message,Throwable t) {
		super(message, t);
	}
}
