package stack;

import java.util.Stack;

/**
 * The expression num = 10 * num + (ch - '0'); is commonly used in Java (and other programming languages) to convert a sequence of numeric characters (e.g., '1', '2', '3') into an integer value.
 *
 * Explanation
 * Character to Integer Conversion (ch - '0'):
 *
 * Characters in Java are represented as Unicode values.
 * For example, the character '0' has a Unicode value of 48, '1' is 49, '2' is 50, and so on.
 * Subtracting '0' from a digit character gives the integer value of the character:
 * '3' - '0' = 51 - 48 = 3
 * Accumulating the Result (10 * num + (ch - '0')):
 *
 * This part builds the number digit by digit.
 * 10 * num shifts the current value of num one decimal place to the left.
 * For example, if num is 12 and you multiply it by 10, it becomes 120.
 * Adding (ch - '0') appends the numeric value of the current digit to num.
 */
public class BasicCalculator {
    public int calculate(String s) {
        int result = 0;
        int sign = 1;
        int number = 0;
        Stack<Integer> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                number = number * 10 + (ch - '0');
            } else if (ch == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (ch == '-') {
                result += sign * number;
                number = 0;
                sign = -1; //Record the sign while processing next operand
            } else if (ch == '(') {
                stack.push(result);
                stack.push(sign);
                //Reset the value to process the content inside ()
                result = 0;
                sign = 1;
            } else if (ch == ')') {
                result += sign * number;
                number = 0;
                result *= stack.pop();
                result += stack.pop();
            }
        }
        return result + (sign * number);
    }
}
