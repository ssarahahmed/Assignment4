import java.util.LinkedList;

public class Balance {

    public static String isBalanced(String s) {
        LinkedList<Character> pair = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {

            char symbol = s.charAt(i);

            // check if symbol is a valid input
            if (symbol != '(' && symbol != ')' &&
                    symbol != '{' && symbol != '}' &&
                    symbol != '<' && symbol != '>' &&
                    symbol != '[' && symbol != ']' &&
                    symbol != '¿' && symbol != '?') {

                return "ERROR: Invalid input. Only ( ) { } < > [ ] ¿ ? are allowed.";
            }

            // if opening symbol, push onto the front of the stack
            if (symbol == '(' || symbol == '{' || symbol == '[' || symbol == '<' || symbol == '¿') {
                pair.add(0, symbol);  // add to front so peek() sees it

                // otherwise it must be a closing symbol, check for match
            } else {
                // if stack is empty there is no opening symbol to match
                if (pair.isEmpty()) {
                    return "NO";
                }

                char open = pair.peek();

                // if open matches the closing symbol, pop the stack
                if      (symbol == ')' && open == '(') pair.remove();
                else if (symbol == '}' && open == '{') pair.remove();
                else if (symbol == ']' && open == '[') pair.remove();
                else if (symbol == '>' && open == '<') pair.remove();
                else if (symbol == '?' && open == '¿') pair.remove();
                    // closing symbol does not match the opener, not balanced
                else return "NO";
            }
        }

        // if stack is empty all symbols were matched
        if (pair.isEmpty()) {
            return "YES";
        } else {
            return "NO";
        }
    }
}