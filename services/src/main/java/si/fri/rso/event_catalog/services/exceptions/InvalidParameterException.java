package si.fri.rso.event_catalog.services.exceptions;

public class InvalidParameterException extends Exception {

    private static final long serialVersionUID = -5681698590543282860L;

    public InvalidParameterException() {
        super();
    }

    /**
     * @param message the message for this exception
     */
    public InvalidParameterException(String message) {
        super(message);
    }

    /**
     * @param cause the root cause
     */
    public InvalidParameterException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message the message for this exception
     * @param cause   the root cause
     */
    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
