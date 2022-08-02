/**
 * This is an interface of a math operation used to calculate two operands.
 * @author Jianfen Deng ON 2020-10-04
 * @version 1.0
 */
public interface Operation {
    /**
     * Returns operation symbol to the user.
     * @return operation symbol
     */
    char getSymbol();
    /**
     * Performs a math operation.
     * @param operandA operand A
     * @param operandB operand B
     * @return a result of an operation
     */
    int perform(int operandA, int operandB);
}
