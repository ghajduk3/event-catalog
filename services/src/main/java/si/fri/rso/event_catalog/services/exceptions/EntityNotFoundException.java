package si.fri.rso.event_catalog.services.exceptions;


public class EntityNotFoundException extends Exception {

    private static final long serialVersionUID = 3898168654534087180L;

    public EntityNotFoundException() {
        super();
    }

    /**
     * @param message the message for this exception
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * @param cause the root cause
     */
    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message the message for this exception
     * @param cause   the root cause
     */
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}