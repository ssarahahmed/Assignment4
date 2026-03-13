
import java.util.LinkedList;

public class Decode {
    public static String Decode(String s) {
        LinkedList<String> stack = new LinkedList<>();

        String current = "";
        int k = 0;

        for (int i = 0; i < s.length(); i++) {
            char symbol = s.charAt(i);

            // keep track of how many times to repeat
            if (Character.isDigit(symbol)) {
                k = k * 10 + (symbol - '0');

                // found a [ so store what we have so far
            } else if (symbol == '[') {
                // push the current string built so far
                stack.add(0, current);
                // push the repeat count as a string marker
                stack.add(0, String.valueOf(k));

                // reset for what's inside the brackets
                current = "";
                k = 0;

                // found a ] so repeat the string inside
            } else if (symbol == ']') {
                // top of stack is the repeat count marker
                int repeat = Integer.parseInt(stack.peek());
                stack.remove();

                // next is the string that came before the bracket
                String previous = stack.peek();
                stack.remove();

                // repeat current string k times
                String repeated = "";
                for (int j = 0; j < repeat; j++) {
                    repeated = repeated + current;
                }

                // combine previous string with the repeated part
                current = previous + repeated;

                // otherwise it's a letter, so add to current string
            } else {
                current = current + symbol;
            }
        }

        // push final result onto stack and display
        stack.add(0, current);
        return stack.peek();
    }


    public static void main(String[] args) {
        System.out.println(Decode("3[a]2[bc]"));       // aaabcbc
        System.out.println(Decode("3[a2[c]]"));        // accaccacc
        System.out.println(Decode("2[abc]3[cd]ef"));   // abcabccdcdcdef
    }
}
