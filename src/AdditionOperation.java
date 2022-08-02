/**
 * Describes an addition operation extended from abstract operation.
 * @author Jianfen Deng ON 2020-10-04
 * @version 1.0
 */
public class AdditionOperation extends AbstractOperation {
    /**
     * The addition code is {@value}.
     */
    public static final char ADDITION_CODE = '+';
    /**
     * Constructs an object of type operation.
     */
    public AdditionOperation() {
        super(ADDITION_CODE);
    }
    /**
     * Performs an addition operation.
     * @param operandA operand A
     * @param operandB operand B
     * @return a result of adding the first and second operands
     */
    @Override
    public int perform(final int operandA, final int operandB) {
        return operandA + operandB;
    }
}
