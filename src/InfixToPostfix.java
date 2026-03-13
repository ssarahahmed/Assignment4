import java.util.LinkedList;

public class InfixToPostfix {

    public static int order(char op) {
        if (op == '^') return 3;
        if (op == '*' || op == '/') return 2;
        if (op == '+' || op == '-') return 1;
        return 0;
    }

    public static String infixToPostfix(String s) {
        // put every character into stack first
        LinkedList<Character> inputStack = new LinkedList<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            inputStack.add(0, s.charAt(i));
        }

        LinkedList<Character> operatorStack = new LinkedList<>();
        String result = "";

        // process one character at a time from the input stack
        while (!inputStack.isEmpty()) {
            char symbol = inputStack.peek();
            inputStack.remove();

            if (Character.isLetter(symbol)) {
                // operand goes straight to result
                result = result + symbol;

            } else if (symbol == '(') {
                // push opening bracket onto operator stack
                operatorStack.add(0, symbol);

            } else if (symbol == ')') {
                // pop operators to result until we find the matching opening bracket
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    result = result + operatorStack.peek();
                    operatorStack.remove();
                }
                // throw away the opening bracket
                operatorStack.remove();

            } else {
                // pop any operators with higher or equal order before pushing current one
                while (!operatorStack.isEmpty() && order(operatorStack.peek()) >= order(symbol)) {
                    result = result + operatorStack.peek();
                    operatorStack.remove();
                }
                operatorStack.add(0, symbol);
            }
        }

        // pop whatever operators are left on the stack
        while (!operatorStack.isEmpty()) {
            result = result + operatorStack.peek();
            operatorStack.remove();
        }

        return result;
    }

}