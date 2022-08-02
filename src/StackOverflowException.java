/**
 * If a user tries to push value into a full stack, then a
 * <tt>StackOverflowException</tt> will be thrown.
 * @author Jianfen Deng ON 2020-10-05
 * @version 1.0
 */
public class StackOverflowException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message when this exception is thrown.
     */
    public StackOverflowException(final String message) {
        super(message);
    }
}
