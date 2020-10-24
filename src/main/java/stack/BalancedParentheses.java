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
	public static List<String>  OPEN_PARAEN = Arrays.asList(new String []  {"{",  "(",  "["});
	public static List<String>  CLOSE_PARAEN = Arrays.asList(new String [] {"}",  ")",  "]"});

	
	public static void main(String[] args) {
		
		String[] parentheses = {"{", "{", "(", ")", "}", "}"};
//		String[] parentheses = {"{", "{", "(", ")", ")", "}", "}"};

		Stack validate = new Stack();
		
		for(int count = 0; count <  parentheses.length; count++) {
			String paranthese = parentheses[count];
			int index = CLOSE_PARAEN.indexOf(paranthese);
			
			if(index == -1) {
				validate.push(paranthese);
			} else {
				//Get the open paranthese for corresponding close paranthese.
				paranthese = OPEN_PARAEN.get(index);
				if(!paranthese.equals(validate.pop())) {
					System.out.println("Invalid paranthese found: " + parentheses[count] + " at index: " + count);
					return;
				}
			}
		}
		System.out.println("Given input has valid order of parentheses");
	}
}
