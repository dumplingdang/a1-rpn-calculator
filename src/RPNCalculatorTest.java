import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the RPNCalculator class.
 *
 * @author BCIT
 * @version 2020
 */
public class RPNCalculatorTest {
    private RPNCalculator calculator;
    @Test
    public void constructorRejectsStackSizeOne() {
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> calculator = new RPNCalculator(RPNCalculator.MIN_STACK_SIZE - 1));
    }
    @Test
    public void constructorRejectsStackSizeZero() {
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> calculator = new RPNCalculator(0));
    }
    @Test
    public void constructorRejectsNegativeStackSize() {
        final int negativeInteger = -1;
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> calculator = new RPNCalculator(negativeInteger));
    }
    @Test
    public void performRejectsNullArgument() {
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> {
                                    calculator = new RPNCalculator(2);
                                    calculator.push(1);
                                    calculator.push(1);
                                    calculator.perform(null);
                                });
    }
    @Test
    public void performAcceptsAddition() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(2);
        calculator.push(1);
        calculator.push(1);
        calculator.perform(new AdditionOperation());
        final int expected = 2;
        final int actual = calculator.getResult();
        assertEquals(expected, actual);
    }
    @Test
    public void performAcceptsSubtraction() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(2);
        calculator.push(1);
        calculator.push(1);
        calculator.perform(new SubtractionOperation());
        final int expected = 0;
        final int actual = calculator.getResult();
        assertEquals(expected, actual);
    }
    @Test
    public void performAcceptsMultiplication() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(2);
        calculator.push(1);
        calculator.push(1);
        calculator.perform(new MultiplicationOperation());
        final int expected = 1;
        final int actual = calculator.getResult();
        assertEquals(expected, actual);
    }
    @Test
    public void performAcceptsDivision() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(2);
        calculator.push(1);
        calculator.push(1);
        calculator.perform(new DivisionOperation());
        final int expected = 1;
        final int actual = calculator.getResult();
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaRejectsNullArgument() {
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> {
                                    calculator = new RPNCalculator(2);
                                    calculator.processFormula(null);
                                });
    }
    @Test
    public void processFormulaRejectsEmptyArgument() {
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> {
                                    calculator = new RPNCalculator(2);
                                    calculator.processFormula("");
                                });
    }
    @Test
    public void processFormulaAddZeros() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(2);
        final int expected = 0;
        String input = "0 0 +";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaSubtractZeros() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(2);
        final int expected = 0;
        String input = "0 0 -";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaMultiplyZeros() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(2);
        final int expected = 0;
        String input = "0 0 *";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaDivideZeros() {
        Assertions.assertThrows(ArithmeticException.class,
                                () -> {
                                    calculator = new RPNCalculator(2);
                                    final int expected = 0;
                                    String input = "0 0 /";
                                    final int actual = calculator.processFormula(input);
                                });
    }
    @Test
    public void processFormulaInvalidOperation() {
        Assertions.assertThrows(InvalidOperationTypeException.class,
                                () -> {
                                    calculator = new RPNCalculator(2);
                                    final int expected = 0;
                                    String input = "0 0 P";
                                    final int actual = calculator.processFormula(input);
                                });
    }
    @Test
    public void processFormulaStackOverflow() {
        Assertions.assertThrows(StackOverflowException.class,
                                () -> {
                                    calculator = new RPNCalculator(2);
                                    final int expected = 0;
                                    String input = "0 0 0 +";
                                    final int actual = calculator.processFormula(input);
                                });
    }
    @Test
    public void processFormulaStackUnderflow() {
        Assertions.assertThrows(StackUnderflowException.class,
                                () -> {
                                    calculator = new RPNCalculator(3);
                                    final int expected = 0;
                                    String input = "0 0 + +";
                                    final int actual = calculator.processFormula(input);
                                });
    }
    @Test
    public void processFormulaDivisionDoesNotGenerateFloats() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(2);
        final int expected = 0;
        String input = "1 2 /";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaDivisionUsesIntegerFloor() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(2);
        final int expected = 4;
        String input = "9 2 /";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaMultipleAdditions() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(10);
        final int expected = 55;
        String input = "1 2 3 4 5 6 7 8 9 10 + + + + + + + + +";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaMultipleSubtractions() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(10);
        final int expected = -53;
        String input = "1 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 -";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaMultipleMultiplications() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(10);
        final int expected = 1024;
        String input = "2 2 2 2 2 2 2 2 2 2 * * * * * * * * *";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaMultipleDivisions() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(10);
        final int expected = 2;
        String input = "1024 2 / 2 / 2 / 2 / 2 / 2 / 2 / 2 / 2 /";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaNegativeIntegers() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(3);
        final int expected = -17;
        String input = "-5 -7 + 2 / 10 10 + + 31 -";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaTreatsZerosAsZeros() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(3);
        final int expected = 2;
        String input = "5 5 - 6 0 * + 1 1 + +";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaUnholyMessOne() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(3);
        final int expected = 11;
        String input = "10 12 + 1 3 + / 3 * 8 4 - -";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaUnholyMessTwo() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(3);
        final int expected = 2;
        String input = "5 5 - 6 0 * + 1 1 + +";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaUnholyMessThree() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(4);
        final int expected = 5;
        String input = "15 7 1 1 + - / 3 * 2 1 1 + + -";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaUnholyMessFour() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(4);
        final int expected = 2;
        String input = "4 2 5 * + 1 3 2 * + /";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
    @Test
    public void processFormulaUnholyMessFive() throws InvalidOperationTypeException, StackUnderflowException, StackOverflowException {
        calculator = new RPNCalculator(4);
        final int expected = 2;
        String input = "2 5 * 4 + 3 2 * 1 + /";
        final int actual = calculator.processFormula(input);
        assertEquals(expected, actual);
    }
}
