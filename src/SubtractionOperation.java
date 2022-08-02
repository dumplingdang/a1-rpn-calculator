/**
 * Describes a subtraction operation extended from abstract operation.
 * @author Jianfen Deng ON 2020-10-04
 * @version 1.0
 */
public class SubtractionOperation extends AbstractOperation {
    /***
     * The subtraction code is {@value}.
     */
    public static final char SUBTRACTION_CODE = '-';
    /**
     * Constructs an object of type operation.
     */
    public SubtractionOperation() {
        super(SUBTRACTION_CODE);
    }
    /**
     * Performs a subtraction operation.
     * @param operandA operand A
     * @param operandB operand B
     * @return a result of subtracting the first operand by the second
     */
    @Override
    public int perform(final int operandA, final int operandB) {
        return operandA - operandB;
    }
}
