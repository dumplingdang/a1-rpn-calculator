import java.util.Scanner;

/***
 * Describes a Reverse Polish Notation Calculator.
 * @author Jianfen Deng
 * @version 1.0
 */
public class RPNCalculator {
    /***
     * The minimum stack size is {@value}.
     */
    public static final int   MIN_STACK_SIZE = 2;
    private final       Stack stack;
    /***
     * Constructs a class type RPNCalculator.
     * @param stackSize size of a stack storing operands
     * @throws IllegalArgumentException throws illegal argument exception when the param
     * stackSize is less than 2
     */
    public RPNCalculator(final int stackSize) {
        if (stackSize < MIN_STACK_SIZE) {
            throw new IllegalArgumentException("Stack size must be at least 2");
        }
        stack = new Stack(stackSize);
    }
    /***
     * Processes a formula passed in by a user.
     * @param formula a formula string which includes operands and operation(s)
     * @return an integer result following the RPN rules
     * @throws StackOverflowException throws stack overflow exception when a user tries to push a
     * operand to a full stack
     * @throws InvalidOperationTypeException throws invalid operation type exception when the
     * operation type passed in is not any of + - * /
     * @throws StackUnderflowException throws stack underflow exception when a user tries to pop
     * or peek a operand from an empty stack
     */
    public int processFormula(final String formula) throws StackOverflowException,
                                                           InvalidOperationTypeException,
                                                           StackUnderflowException {
        String    symbol;
        Operation operation;
        if (formula == null || formula.trim().isEmpty()) {
            throw new IllegalArgumentException("Formula cannot be null or empty");
        }
        Scanner scanner = new Scanner(formula.trim());
        while (scanner.hasNext()) {
            while (scanner.hasNextInt()) {
                push(scanner.nextInt());
            }
            symbol    = scanner.next();
            operation = getOperation(symbol.charAt(0));
            perform(operation);
        }
        scanner.close();
        return getResult();
    }
    /***
     * Pushes an operand into a stack.
     * @param operand an operand passed in from the formula string provided in the processFormula
     *                () method
     * @throws StackOverflowException throws stack overflow exception when a user tries to push a
     * operand into a full stack
     */
    public void push(final int operand) throws StackOverflowException {
        if (stack.unused() == 0) {
            throw new StackOverflowException("This stack is full!");
        }
        stack.push(operand);
    }
    /*
     Helper method to get appropriate operation given a symbol scanned from processFormula() method.
     */
    private Operation getOperation(final char symbol) throws InvalidOperationTypeException {
        return switch (symbol) {
            case '+' -> new AdditionOperation();
            case '-' -> new SubtractionOperation();
            case '*' -> new MultiplicationOperation();
            case '/' -> new DivisionOperation();
            default -> throw new InvalidOperationTypeException("formula can only contain "
                                                               + "integers, +, -, *, and /");
        };
    }
    /***
     * Performs a calculation given a operation passed in and pushes it into a stack as the last
     * element.
     * @param operation an operation obtained from getOperation() method
     * @throws StackUnderflowException throws stack underflow exception if a user tries to pop
     * out an operand from an empty stack
     * @throws StackOverflowException throws stack overflow exception if a users tries to push
     * the calculation result into a full stack
     */
    protected void perform(final Operation operation) throws StackUnderflowException,
                                                             StackOverflowException {
        int operandA;
        int operandB;
        int result;
        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null!");
        }
        operandB = stack.pop();
        operandA = stack.pop();
        result   = operation.perform(operandA, operandB);
        stack.push(result);
    }
    /***
     * Gets a calculation result from a stack.
     * @return a calculation result from a stack.
     * @throws StackUnderflowException throws stack underflow exception when a user tries to take
     * a peek of calculation result from an empty stack
     */
    public int getResult() throws StackUnderflowException {
        int valueRetrieved;
        if (stack.capacity() == 0) {
            throw new StackUnderflowException("There are no operands!");
        }
        valueRetrieved = stack.peek();
        return valueRetrieved;
    }
    /**
     * Drives the program by evaluating the RPN calculation provided as
     * a command line argument.
     * Example usage: RPNCalculator 10 "1 2 +"
     * Note that the formula MUST be placed inside of double quotes.
     * @param argv - the command line arguments are the size of the Stack
     * to be created followed by the expression to evaluate.
     */
    public static void main(final String[] argv) {
        // Checks for correct number of command line arguments.
        if (argv.length != 2) {
            System.err.println("Usage: Main <stack size> <formula>");
            System.exit(1);
        }

        // Initializes stack and RPNCalculator.
        final int           stackSize  = Integer.parseInt(argv[0]);
        final RPNCalculator calculator = new RPNCalculator(stackSize);

        try {
            System.out.println("[" + argv[1] + "] = "
                               + calculator.processFormula(argv[1]));
        } catch (final InvalidOperationTypeException ex) {
            System.err.println("formula can only contain integers, +, -, *, and /");
        } catch (final StackOverflowException ex) {
            System.err.println("too many operands in the formula, increase the stack size");
        } catch (final StackUnderflowException ex) {
            System.err.println("too few operands in the formula");
        }
    }
}
