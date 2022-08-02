/**
 * If the type of an operation scanned from a formula is not any of + - * /, then a
 * <tt>InvalidOperationTypeException</tt> will be thrown.
 * @author Jianfen Deng ON 2020-10-06
 * @version 1.0
 */
public class InvalidOperationTypeException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message when this exception is thrown.
     */
    public InvalidOperationTypeException(final String message) {
        super(message);
    }
}
