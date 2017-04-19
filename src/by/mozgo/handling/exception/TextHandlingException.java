package by.mozgo.handling.exception;

/**
 * @author Andrei Mozgo
 */
public class TextHandlingException extends Exception {

    public TextHandlingException(String message) {
        super(message);
    }

    public TextHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextHandlingException(Throwable cause) {
        super(cause);
    }

    public TextHandlingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}