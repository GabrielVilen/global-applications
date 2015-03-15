package se.kth.iv1201projekt.exception;

/**
 * This exception is used to represent a failed attempt to login.
 *
 * @author Kim
 */
public class LoginErrorException extends Exception {

    public LoginErrorException() {
        super();
    }

    public LoginErrorException(String message) {
        super(message);
    }

    public LoginErrorException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
