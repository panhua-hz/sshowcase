package andrew.pizza.domain;

public class CustomerNotFoundException extends Exception {
	private static final long serialVersionUID = -4544772002200211849L;

	public CustomerNotFoundException() {
		super();
		
	}

	public CustomerNotFoundException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(msg, cause, enableSuppression, writableStackTrace);
		
	}

	public CustomerNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
		
	}

	public CustomerNotFoundException(String msg) {
		super(msg);
		
	}

	public CustomerNotFoundException(Throwable cause) {
		super(cause);

	}
	
	
}
