import java.util.Arrays;

/**
 * Describes a class of type Stack.
 * @author Jianfen Deng ON 2020-10-05
 * @version 1.0
 */
public class Stack {
    /***
     * The minimum size of a stack is {@value}.
     */
    public static final int   MIN_SIZE_OF_STACK = 1;
    private final       int[] stackValues;
    private             int   count;
    /**
     * Constructs a class type Stack.
     * @param stackSize size of a Stack
     * @throws IllegalArgumentException throw illegal argument exception when a user tries to set
     * the stack size less than 1
     */
    public Stack(final int stackSize) {
        if (stackSize < MIN_SIZE_OF_STACK) {
            throw new IllegalArgumentException("stack size can't be less than 1");
        }
        stackValues = new int[stackSize];
    }
    /**
     * Returns the capacity of the Stack.
     * @return the capacity of the Stack
     */
    public int capacity() {
        return stackValues.length;
    }
    /**
     * Returns the number of elements in the Stack.
     * @return the number of elements in the Stack
     */
    private int size() {
        count++;
        return count;
    }
    /**
     * Returns the amount of space left in the Stack.
     * @return the amount of space left in the Stack
     */
    public int unused() {
        return capacity() - count;
    }
    /**
     * Adds value to the Stack.
     * @param value a value to be added to the Stack
     * @throws StackOverflowException throws stack overflow exception when a user tries to push a
     * value into a full stack
     */
    public void push(final int value) throws StackOverflowException {
        int indexOfArray = size() - 1;
        if (count > capacity()) {
            throw new StackOverflowException("This stack is full!");
        }
        stackValues[indexOfArray] = value;
    }
    /**
     * Returns a value popped out from the Stack.
     * @return a value popped out from the Stack
     * @throws StackUnderflowException throws stack underflow exception when a user tries to pop
     * out a value from an empty stack
     */
    public int pop() throws StackUnderflowException {
        int valuePopped;
        if (count == 0) {
            throw new StackUnderflowException("Cannot call Stack.pop() on an empty stack!");
        } else {
            valuePopped = stackValues[count - 1];
            count--;
        }
        return valuePopped;
    }
    /***
     * Returns a value peeked from the stack.
     * @return a value peeked from the stack.
     * @throws StackUnderflowException throws stack underflow exception when a user tries to peek
     * a value from an empty stack
     */
    public int peek() throws StackUnderflowException {
        int valuePeeked;
        if (count == 0) {
            throw new StackUnderflowException("Cannot call Stack.peek() on an empty stack!");
        }
        valuePeeked = stackValues[count - 1];
        return valuePeeked;
    }
    /***
     * Indicates whether a given object is equal to this one.
     * @param o a given object to compare with this one
     * @return {@code true} if a given object has the same type, same used size and each element
     * in the stack are the same with this one. {@code false} otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!this.getClass().equals(o.getClass())) {
            return false;
        }
        Stack stack = (Stack) o;
        if (count != stack.count) {
            return false;
        }
        return Arrays.equals(stackValues, stack.stackValues);
    }
    /***
     * Returns a hash code of a Stack object corresponding with the {@code equal} method.
     * @return a hash code of a Stack object
     */
    @Override
    public int hashCode() {
        final int primeNumber = 31;
        int result = Arrays.hashCode(stackValues);
        result = primeNumber * result + count;
        return result;
    }
    /***
     * Creates a string representation of a Stack object.
     * @return a string representation of a Stack object
     */
    @Override
    public String toString() {
        return "Stack{" + "stackValues=" + Arrays.toString(stackValues) + ", count=" + count + '}';
    }
}
