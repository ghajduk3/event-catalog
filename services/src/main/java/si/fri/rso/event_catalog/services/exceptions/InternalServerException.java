package si.fri.rso.event_catalog.services.exceptions;

public class InternalServerException extends Exception {

    private static final long serialVersionUID = 8800423362209593156L;

    public InternalServerException() {
        super();
    }

    /**
     * @param message the message for this exception
     */
    public InternalServerException(String message) {
        super(message);
    }

    /**
     * @param cause the root cause
     */
    public InternalServerException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message the message for this exception
     * @param cause   the root cause
     */
    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }
}