/**
 * If a user tries to pop out or peek value from an empty stack, then a
 * <tt>StackUnderflowException</tt> will be thrown.
 * @author Jianfen Deng ON 2020-10-05
 * @version 1.0
 */
public class StackUnderflowException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message when this exception is thrown.
     */
    public StackUnderflowException(final String message) {
        super(message);
    }
}
