package se.kth.iv1201projekt.exception;

/**
 * This exception is used to show that an operation needs the user to be logged
 * in to be called.
 *
 * @author Kim
 */
public class NotLoggedInException extends Exception {

    public NotLoggedInException() {
        super();
    }

    public NotLoggedInException(String message) {
        super(message);
    }

    public NotLoggedInException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
