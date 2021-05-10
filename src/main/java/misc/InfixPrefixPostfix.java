package misc;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class InfixPrefixPostfix {
	public static void main(String[] args) {
		/**
		 *   Infix expression
		 *   ---------------
		 *   2 + 3 
		 *   <Operand> <OPERATOR> <Operand>
		 *   Operand = Object or value on which operation is performed.
		 *           = Operand can be variable or constants
		 *           = Operand can also be again an expression which can be further divided into <Operand> <OPERATOR> <Operand>
		 *           
		 *   Binary Operator = which requires exactly two operands to perform operation.
		 *   
		 *   
		 *   Postfix expression (Reverse polish)
		 *   ----------------------------------
		 *   2 3 + 
		 *   <Operand> <Operand> <OPERATOR> 
		 *   
		 *   Prefix expression (Polish) Should come from (Right to Left) while evaluating
		 *   -----------------------------
		 *   + 2 3 
		 *   <OPERATOR> <Operand> <Operand>  
		 *   
		 *   
		 *   Order of Operation / Precedence and Associative rule:
		 *   -------------------
		 *   1) Parentheses () {} []
		 *   2) Exponents ^  (2^3^4)  (Right to left for multiple exponents)
		 *   3) Multiplication and Division shares same precedence and evaluated left to right associative rule.
		 *   4) Addition and Subtraction shares same precedence and evaluated left to right.
		 *   
		 *   
		 *   A + B * c     
		 *   A + (B * C)
		 *   A + (B C *)
		 *   A B C * +   <--- Postfix expression created without parenthesis though input has ()
		 */
		
		
		// http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
		// http://stackoverflow.com/questions/16217627/string-split-at-a-meta-character
		// http://stackoverflow.com/questions/13948751/string-parse-error
//		String expression = "4+1*2+3-1/2"; //8.5
//		String expression = "4+1+2*6"; //17
		String expression = "((2+3)*6-10)*2"; //40
		String[] expressionArray = arrayConversionOfExpression(expression);
		System.out.println("expressionArray: " + Arrays.toString(expressionArray));
//		String postfixExpression = convertInfixToPostfix(expressionArray);
		String postfixExpression = convertInfixToPostfixWithParentheses(expressionArray);
		System.out.println("postfixExpression: " + postfixExpression);
		String[] postfixExpressionArray = postfixExpression.split(",");
		System.out.println("postfixExpressionArray: " + Arrays.toString(postfixExpressionArray));
		double result = evaluatePostfix(postfixExpressionArray);
		System.out.println("result: "  + result);
	}

	enum Operators {

		PLUS("+", 1) {
			@Override
			public double compute(double i, double j) {
				return i + j;
			}
		},
		MINUS("-", 1) {
			@Override
			public double compute(double i, double j) {
				return i - j;
			}
		}, 
		STAR("*" , 2) {
			@Override
			public double compute(double i, double j) {
				return i * j;
			}
		},
		SLASH("/" , 2) {
			@Override
			public double compute(double i, double j) {
				return i / j;
			}
		}, 
		PARENTHESIS_START("(", 0) {
			@Override
			public double compute(double i, double j) {
				return 0;
			}
		},
		PARENTHESIS_END("(", 3) {
			@Override
			public double compute(double i, double j) {
				return 0;
			}
		};
		
		String operator;
		int precedence;
		
		private Operators(String operator, int value) {
			this.operator = operator;
			this.precedence = value;
		}
		
		public boolean isGreaterOrEqualPrecedence(Operators passed) {
			return this.precedence >= passed.precedence;
		}
		
		public abstract double compute(double i, double j);
	}

	static Map<String, Operators> OperatorsMap = Map.of("+", Operators.PLUS,
														"-", Operators.MINUS,
														"*", Operators.STAR,
														"/", Operators.SLASH,
														"(", Operators.PARENTHESIS_START,
														")", Operators.PARENTHESIS_END);
	
	private static String convertInfixToPostfixWithParentheses(String[] expressionArray) {

		/**
		 * When you found closing parentheses pop operators till opening parentheses.
		 * 1) If it is operand simply add it to the appender
		 * 2) We can not append the operator directly; we need to look at next operator
		 *    So put into stack
		 * 3) If next operator is lesser/equal of the one in the top of the stack; then stack is popped out till empty
		 * and then added with this lower value.
		 *    e.g) if stack has * then + is compared then * is popped out and then + will be added into the stack.
		 *    If next operator is greater simply add it into stack.
		 */
		StringBuilder postFix = new StringBuilder();
		Stack<String> stack = new Stack<String>();
		for(String expression : expressionArray) {
			 if(!OperatorsMap.containsKey(expression)) { // equals to isNumeric(String str)
				 postFix.append(expression); //operand
				 postFix.append(',');
			 } else {
				if(!stack.isEmpty() && OperatorsMap.get(expression) == Operators.PARENTHESIS_END) {
					while(!stack.isEmpty() && OperatorsMap.get(stack.peek()) != Operators.PARENTHESIS_START) {
						postFix.append(stack.pop());
						postFix.append(',');
					}
					stack.pop(); //Remove OperatorPrecedence.PARENTHESIS_START
				} else if(!stack.isEmpty() && OperatorsMap.get(stack.peek()).isGreaterOrEqualPrecedence(
						OperatorsMap.get(expression))) {
					while(!stack.isEmpty() && OperatorsMap.get(stack.peek()) != Operators.PARENTHESIS_START) {
						postFix.append(stack.pop());
						postFix.append(',');
					}
					stack.push(expression);
				} else {
					stack.push(expression);
				}
			 }
		}
		while(!stack.isEmpty()) {
			postFix.append(stack.pop());
			postFix.append(',');
		}
		String output = postFix.toString();
		return output.substring(0, output.length() - 1);
	}

	private static double evaluatePostfix(String[] postfixExpressionArray) {

		Stack<Double> stack = new Stack<>();
		for(String value : postfixExpressionArray) {
			if(!OperatorsMap.containsKey(value)) { // equals to isNumeric(String str)
				stack.push(Double.parseDouble(value));
			}
			else {
				//Important preserve the order.
				double j = stack.pop(); //So J should come first
				double i = stack.pop();
				Operators operator = OperatorsMap.get(value);
				stack.push(operator.compute(i, j));
			}
		}
		return stack.pop();
	}

	private static String[] arrayConversionOfExpression(String expression) {

		expression = expression.replaceAll("\\+", "~+~");
		expression = expression.replaceAll("\\*", "~*~");
		expression = expression.replaceAll("-", "~-~");
		expression = expression.replaceAll("/+", "~/~");
		expression = expression.replaceAll("\\(", "~(~"); //also you can use [(] instead of \\(
		expression = expression.replaceAll("\\)", "~)~"); //also you can use [)] instead of \\)
		expression = expression.replaceAll("~~", "~");
		if (expression.startsWith("~")) {
			expression = expression.substring(1);
		}
		return expression.split("~");
	}

	private static String convertInfixToPostfix(String[] expressionArray) {
		/**
		 * 1) If it is operand simply add it to the appender
		 * 2) We can not append the operator directly; we need to look at next operator
		 *    So put into stack
		 * 3) If next operator is lesser of the one in the top of the stack; then stack is popped out till empty and then added with this lower value.
		 *    e.g) if stack has * then + is compared then * is popped out and then + will be added into the stack.
		 *    If next operator is equal or greater simply add it into stack.
		 */
		StringBuilder postFix = new StringBuilder();
		Stack<String> stack = new Stack<String>();
		for(String value : expressionArray) {
			if(!OperatorsMap.containsKey(value)) { // equals to isNumeric(String str)
				postFix.append(value);
				postFix.append(',');
			}
			else {
				if(!stack.isEmpty() && OperatorsMap.get(stack.peek()).isGreaterOrEqualPrecedence(
						OperatorsMap.get(value))) {
					while(!stack.isEmpty()) {
						postFix.append(stack.pop());
						postFix.append(',');
					}
				}
				stack.push(value);
			}
		}
		while(!stack.isEmpty()) {
			postFix.append(stack.pop());
			postFix.append(',');
		}
		String output = postFix.toString();
		return output.substring(0, output.length() - 1);
	}
}
