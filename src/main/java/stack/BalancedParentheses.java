package stack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/
 * 
 * @author kanagavelusugumar
 *
 */
public class BalancedParentheses {

	public static List<String>  OPEN_PARENTHESES = Arrays.asList("{", "(", "[");
	public static List<String>  CLOSE_PARENTHESES = Arrays.asList("}", ")", "]");

	public static void main(String[] args) {
		
		String[] inputs = {"{", "{", "(", ")", "}", "}"};
//		String[] parentheses = {"{", "{", "(", ")", ")", "}", "}"};

		Stack validate = new Stack();
		
		for(int idx = 0; idx <  inputs.length; idx++) {
			String parenthesis = inputs[idx];
			int closeParenthesisIndex = CLOSE_PARENTHESES.indexOf(parenthesis);
			
			if(closeParenthesisIndex == -1) {
				validate.push(parenthesis);
			} else {
				//Get the open parenthesis for corresponding close parenthesis.
				parenthesis = OPEN_PARENTHESES.get(closeParenthesisIndex);
				if(!parenthesis.equals(validate.pop())) {
					System.err.println("Invalid parenthesis found: " + inputs[idx] + " at index: " + idx);
					return;
				}
			}
		}
		System.out.println("Given input has valid order of parentheses");
	}
}
