/**
 * Describes an abstract operation.
 * @author Jianfen Deng ON 2020-10-04
 * @version 1.0
 */
public abstract class AbstractOperation implements Operation {
    /***
     * operationType is char type instance variable.
     */
    protected char operationType;
    /**
     * Constructs an object of type operation.
     * @param operationType operation type
     */
    public AbstractOperation(final char operationType) {
        this.operationType = operationType;
    }
    /***
     * Gets an operation type.
     * @return an operation type
     */
    @Override
    public final char getSymbol() {
        return operationType;
    }
    /***
     * Returns a hash code of each operation.
     * @return a hash code of each operation
     */
    @Override
    public final int hashCode() {
        return operationType;
    }
    /***
     * Indicates whether a given object is equal to this one.
     * @param o an object to be compared with this one.
     * @return {@code true} if two objects are equal and {@code false} otherwise
     */
    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!this.getClass().equals(o.getClass())) {
            return false;
        }
        AbstractOperation that = (AbstractOperation) o;
        return operationType == that.operationType;
    }
    /***
     * Creates a string representation of the type AbstractOperation for each of the operations:
     * +, -, * /.
     * @return a string representation of the type AbstractOperation
     */
    @Override
    public final String toString() {
        return "Operation{" + "operationType=" + operationType + '}';
    }
}
