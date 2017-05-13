package exceptions;

@SuppressWarnings("serial")
public class SSRptException extends RuntimeException {

	public SSRptException() {
		super();

	}

	public SSRptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public SSRptException(String message, Throwable cause) {
		super(message, cause);

	}

	public SSRptException(String message) {
		super(message);

	}

	public SSRptException(Throwable cause) {
		super(cause);

	}

}
