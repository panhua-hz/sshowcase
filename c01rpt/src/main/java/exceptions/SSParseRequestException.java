package exceptions;

public class SSParseRequestException extends RuntimeException {
    private static final long serialVersionUID = 7728339011225317029L;
    public SSParseRequestException(){
	super();
    }
    public SSParseRequestException(String message){
	super(message);
    }
    public SSParseRequestException(String message, Throwable t){
	super(message, t);
    }
    public SSParseRequestException(Throwable t){
	super(t);
    }
    
}
