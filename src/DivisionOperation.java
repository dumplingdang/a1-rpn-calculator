/**
 * Describes a division operation extended from abstract operation.
 * @author Jianfen Deng ON 2020-10-04
 * @version 1.0
 */
public class DivisionOperation extends AbstractOperation {
    /***
     * The division code is {@value}.
     */
    public static final char DIVISION_CODE = '/';
    /**
     * Constructs an object of type operation.
     */
    public DivisionOperation() {
        super(DIVISION_CODE);
    }
    /**
     * Performs a division operation.
     * @param operandA operand A
     * @param operandB operand B
     * @return a result of dividing the first operand by the second
     */
    @Override
    public int perform(final int operandA, final int operandB) {
        return operandA / operandB;
    }
}
