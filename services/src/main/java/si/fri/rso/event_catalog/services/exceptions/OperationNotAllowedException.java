package si.fri.rso.event_catalog.services.exceptions;

public class OperationNotAllowedException extends Exception {

    private static final long serialVersionUID = -7323155214628156774L;

    public OperationNotAllowedException() {
        super();
    }

    /**
     * @param message the message for this exception
     */
    public OperationNotAllowedException(String message) {
        super(message);
    }

    /**
     * @param cause the root cause
     */
    public OperationNotAllowedException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message the message for this exception
     * @param cause   the root cause
     */
    public OperationNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}