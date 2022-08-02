# a1-rpn-calculator
## Introduction
In 1924, a Polish mathematician named Jan Łukasiewicz invented something called Polish notation. It was
refined in the early 1960s by Edsger Dijkstra who developed Reverse Polish Notation to take advantage of
the Stack data structure.

Binary infix operators are called binary because they require two operands. They are called infix because
the operator is placed between the operands:

2 + 2 = 4</br>
4 - 2 = 2</br>
5 / 3 = 1 (assuming we are using ints)

Reverse Polish notation (RPN) is a mathematical notation where operators follow their operands. Instead
of using a binary infix operator, RPN uses a binary postfix operator:

2 2 + = 4</br>
4 2 - = 2</br>
5 3 / = 1

Consider our usual notation. When we mix our operations using binary infix operators, we must implement
rules of precedence. The use of parentheses can result in dramatically different results:

2 - 3 * 4 = -10</br>
(2 - 3) * 4 = -4

RPN (postfix) notation removes the need for parentheses! RPN’s greatest advantage is clear when we
consider expressions that contain more than one operand. If we want an operation to take precedence,
we just put the operator immediately to the right of the two operands:

2 3 4 * - = -10</br>
2 3 - 4 * = -4

## Implementation requirements

1. We are going to build a Reverse Polish Notation calculator. The RPNCalculator will accepts two
command line arguments. The first command line argument must be an int which represents the
initial size of the **Stack** we will use to store operands. The second command line argument must be
a String in double quotes that contains a valid Reverse Polish Notation expression. We will place the
main method inside a class called **RPNCalculator**, and we will invoke the program like this:

    **java RPNCalculator 10 "1 2 3 4 5 6 7 8 9 10 + + + + + + + + +"**

    The program must respond by printing the expression inside a set of square brackets followed by the
    result:

    **[1 2 3 4 5 6 7 8 9 10 + + + + + + + + +] = 55**

2. Start by defining an interface called **Operation**. An **Operation** is a function that has a symbol.
It requires two operands. The **Operation** interface contains two public methods, **char getSymbol()**,
which returns the operation symbol to the user, and **int perform(int operandA, int operandB)**, which
is a blueprint for performing a math operation. That’s all we need to put inside the Operation.

3. Note that the **Operation** doesn’t contain any instance variables because it’s an interface. An interface
just tells us how to interact with something. It makes no demands about implementation. Since it’s
an interface, it doesn’t have a constructor either. We need to create a level of abstraction, another
layer of the onion if you will, that adds the instance variable that will store the actual symbol being
used.

4. Define an abstract class called **AbstractOperation** that implements **Operation**. Ensure **AbstractOperation**
is an abstract class. It must contain a single protected instance variable called **operationType**,
a char. Add a constructor which accepts a char and assigns it to **operationType**. Add an
accessor that returns the char. The accessor must be called **getSymbol()** and it must be final. We
don’t want any subclasses overriding it.

5. We are going to confine our exploration to the basic operations: +, -, \*\, and /. Create four classes
that extend **AbstractOperation**. They must be called **AdditionOperation**, **SubtractionOperation**,
**MultiplicationOperation**, and **DivisionOperation**. Each of these classes must contain a static constant
char called **ADDITION_CODE** or **SUBTRACTION_CODE** or **MULTIPLICATION_CODE** or **DIVISION_
CODE**, each of which is assigned the value ’+’, ’-’, ’*’, or ’/’. The constructor must pass the constant to the superclass constructor. Ensure each
class provides a concrete implementation of perform too. That’s all you need inside each class.

6. We defined the concept of an **Operation**, and we can interact with an **Operation** by getting its symbol and passing it two operands to operate
on. We further added an abstract implementation of **Operation** which added an instance variable,
a constructor to assign it, and an accessor to acquire it. Then we created four concrete implementations
of Operation. Each one is many things at once. For example, an **AdditionOperation** is-an
**AbstractOperation** and it is-an **Operation**, too.

7. We will implement a fixed size, non-resizable **Stack** using
an olde fashioned array of int:
    - The **Stack** must have two instance variables, an array of int called **stackValues**, and an int called
    **count**.
    - The **Stack** constructor must accept an integer representing the size of the array to create. If the
    size is less than one, throw an **IllegalArgumentException**.
    - Add a **capacity( )** method that returns the size of the **Stack**.
    - Add a **size( )** method that returns the number of elements in the **Stack**, i.e., the count.
    - Add a method called **unused( )** which returns the amount of space left in the **Stack**.
    - Add a method called **push(int value)** which accepts an integer called **value**. This method pushes
    the **value** onto the **Stack**. If the **Stack** is already full, this method must throw a checked exception
    called **StackOverflowException**. You must create this exception. Pass the message *"This
    stack is full!"* to the **StackOverflowException** constructor. If, however, there is room, push the
    **value** onto the **Stack**. The next call to pop will remove this **item( )** and return it. The next call
    to **peek( )** will return a reference to this item without removing it.
    - Add a method called **pop( )* which accepts no parameters and returns
    an int. If someone tries to pop a value from an empty **Stack**, ensure this method throws a
    checked exception called **StackUnderflowException**. You will have to add this exception
    to your project too. Pass the message *"Cannot call Stack.pop() on an empty stack!"* to the
    **StackUnderflowException** exception.
    - Finally, add a method called **peek( )** which does NOT remove anything from the **Stack**, but does
    return the value on top of it. If the **Stack** is empty, throw a new **StackUnderflowException** and
    pass the message *"Cannot call Stack.peek() on an empty stack!"*.

8. Implement a class called **RPNCalculator**.
    - **RPNCalculator** contains a single integer constant called **MIN_STACK_SIZE** which stores two.
    The smallest RPN calculation is two operands followed by a single operation.
    - **RPNCalculator** contains a single instance variable of type Stack called (wait for it) **stack**.
    - The constructor must accept an integer called **stackSize**. If this integer is less than **MIN_STACK_SIZE**
    the constructor must throw an **IllegalArgumentException**. Otherwise instantiate a Stack of that
    size and assign the address of this new object to the instance variable.
    - Implement a method called **public int processFormula(final String formula)**:
      - This method must throw an **IllegalArgumentException** if formula is equal to null or if it is
      a string of length zero.
      - Otherwise, instantiate a **Scanner** object, passing the formula to its constructor. We will
      parse the formula using an instance of the **Scanner** class.
      - Here’s the algorithm I’d like you to use. While the **Scanner** object’s **hasNext( )** method
      returns true, check if the **hasNextInt( )** method returns true, too. If it does, we know the
      next token in the formula String is an operand that we can push onto the **Stack**. If this
      is the case, go ahead and push that int onto the stack with a helper method called **void
      push(final int operand)**.
      - The method **void push(final int operand)** must check that **unused( )** does not return
      zero. If it does, we must throw a **StackOverflowException** with an appropriate message.
      Otherwise, push the operand onto the **Stack**.
      - Otherwise, it must be an operation. If it’s an operation, we must scan the operation and
      use it to instantiate the correct **Operation** descendant. Do this in a helper method called
      **private Operation getOperation(final char symbol)**.
      - Inside **private Operation getOperation(final char symbol)**, use a switch statement to evaluate
      symbol. If it’s ’+’, return a new **AdditionOperation** object, if it’s ’-’, return a new
      **SubtractionOperation**, etc.
      - The return value must be assigned to a variable inside the **processFormula** method whose
      data type is **Operation**. This makes our code flexible. Now we can use polymorphism! We
      can create and use any kind of **Operation** we like. We can define new and novel operations.
      All we have to do is add a line inside the switch statement in the **getOperation** method.
      - In the switch statement, the default case must throw a checked exception called **Invalid-
      OperationTypeException** (you will need to create this). Pass the errant operation to the
      **InvalidOperationTypeException** constructor.
      - Otherwise, **processFormulamust** pass the instance of **Operation** created in the helper method
      to a method called **perform( )**, and then invoke a public method called **getResult( )**.
    - public int **getResult()** must use the **peek( )** method in the **Stack** to retrieve the current value in
    the **Stack**, i.e., the result. If the size of the Stack is zero throw a new **StackUnderflowException**
    and pass the message, *"There are no operands!"*.
    - Finally. The one method that rules them all. **private
    void perform(final Operation operation)** will accept the **Operation** object instantiated by
    **processFormula** and its helpers. Check that operation is not null. If it is, throw an **IllegalArgumentException**
    using the message, *"Operation cannot be null!"*. Otherwise, pop the top two
    operands and pass them to the **Operation**’s **perform** method. Use the **push( )** method to push
    the result onto the Stack.
