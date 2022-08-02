/**
 * Describes a multiplication operation extended from abstract operation.
 * @author Jianfen Deng ON 2020-10-04
 * @version 1.0
 */
public class MultiplicationOperation extends AbstractOperation {
    /***
     * The multiplication code is {@value}.
     */
    public static final char MULTIPLICATION_CODE = '*';
    /**
     * Constructs an object of type operation.
     */
    public MultiplicationOperation() {
        super(MULTIPLICATION_CODE);
    }
    /**
     * Performs a multiplication operation.
     * @param operandA operand A
     * @param operandB operand B
     * @return a result of multiplying the first operand by the second
     */
    @Override
    public int perform(final int operandA, final int operandB) {
        return operandA * operandB;
    }
}
