package si.fri.rso.event_catalog.services.exceptions;

public class InvalidEntityException extends Exception {

    private static final long serialVersionUID = -5681698590543282860L;

    public InvalidEntityException() {
        super();
    }

    /**
     * @param message the message for this exception
     */
    public InvalidEntityException(String message) {
        super(message);
    }

    /**
     * @param cause the root cause
     */
    public InvalidEntityException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message the message for this exception
     * @param cause   the root cause
     */
    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}