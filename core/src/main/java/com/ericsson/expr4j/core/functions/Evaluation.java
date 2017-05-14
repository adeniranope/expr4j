package com.ericsson.expr4j.core.functions;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

import com.ericsson.expr4j.core.constants.Functions;
import com.ericsson.expr4j.core.constants.Operators;
import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.ericsson.expr4j.core.exceptions.Expression4JException;
import com.ericsson.expr4j.core.interfaces.CalculableFunction;

/**
 * 
 * Expression processing based on ShuntingYard Algorithm
 * 
 * @author adeniranope
 *
 */

public class Evaluation implements Serializable {

	private static final long serialVersionUID = 3341295000080029886L;

	/**
	 * 
	 * Evaluate a mathematical expression
	 * 
	 * @param infixExpression
	 * @return
	 */
	public static String evaluate(String infixExpression) throws Expression4JException{

		Deque<String> tokenStack = new LinkedList<String>();
		Deque<String> operatorStack = new LinkedList<String>();
		Map<String,Functions> functionMap = Functions.toMap();

		// convert the expression to a postfix expression
		char[] tokens = infixExpression.trim().toCharArray();

		for (int i = 0; i < tokens.length; i++) {

			if (tokens[i] == ' ')
				continue; // skip the token since its a white space

			// construct numbers
			if (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.') {
				StringBuilder numberBuilder = new StringBuilder();
				while (i < tokens.length && (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.')) {
					numberBuilder.append(tokens[i++]);
				}
				tokenStack.push(numberBuilder.toString());
			}

			// construct alphabetic functions
			if (i < tokens.length && tokens[i] >= 'a' && tokens[i] <= 'z' && Character.isAlphabetic(tokens[i])) {
				StringBuilder functionBuilder = new StringBuilder();
				while (i < tokens.length && tokens[i] >= 'a' && tokens[i] <= 'z') {
					functionBuilder.append(tokens[i++]);
				}
				if(!functionMap.containsKey(functionBuilder.toString())){
					throw new Expression4JException(ErrorCode.INVALID_MATH_FUNCTION);
				}
				operatorStack.push(functionBuilder.toString());
			}

			// construct character functions
			if (i < tokens.length && Evaluation.isSpecialCharacters(tokens[i])) {
				StringBuilder functionBuilder = new StringBuilder();
				while (i < tokens.length && Evaluation.isSpecialCharacters(tokens[i])) {
					functionBuilder.append(tokens[i++]);
				}
				if(!functionMap.containsKey(functionBuilder.toString())){
					throw new Expression4JException(ErrorCode.INVALID_MATH_FUNCTION);
				}				
				operatorStack.push(functionBuilder.toString());
				if (!tokenStack.isEmpty() && !tokenStack.peek().equals(")")) {
					tokenStack.push(
							String.valueOf(Evaluation.evaluateOperation(functionBuilder.toString(), tokenStack.pop())));
				}
				operatorStack.pop();
			}

			// check for open braces
			if (i < tokens.length && Evaluation.isOpenParenthesis(tokens[i])) {
				operatorStack.push(String.valueOf(tokens[i]));
			}

			// check for close braces, execute all tokens within brackets and
			// the function that may accompany it
			if (i < tokens.length && Evaluation.isClosedParenthesis(tokens[i])) {
				while (!operatorStack.peek().equals("(")) {
					tokenStack.push(String.valueOf(Evaluation.evaluateOperation(operatorStack.pop().toString(),
							tokenStack.pop(), tokenStack.pop())));
				}
				if (operatorStack.peek().equals("(")) {
					operatorStack.pop();// take off the open bracket
					if (Evaluation.isFunction(operatorStack.peek())) {
						// check for alphabetic functions
						String operator = operatorStack.pop();
						if (functionMap.containsKey(operator)) {
							tokenStack.push(String.valueOf(Evaluation.evaluateOperation(operator, tokenStack.pop())));
						}
					}
				}
			}

			// construct operators
			if (i < tokens.length && new Character(tokens[i]) != null && Evaluation.isOperator(tokens[i])) {
				if(Evaluation.precedence(String.valueOf(tokens[i]),operatorStack.peek()) && !operatorStack.isEmpty()){
					tokenStack.push(String.valueOf(Evaluation.evaluateOperation(operatorStack.pop(),tokenStack.pop(),tokenStack.pop())));
				}
				operatorStack.push(String.valueOf(tokens[i]));
			}
			
		}

		//compute the entire expression
		while(!operatorStack.isEmpty()){
			tokenStack.push(String.valueOf(Evaluation.evaluateOperation(operatorStack.pop(),tokenStack.pop(),tokenStack.pop())));
		}
		
		return tokenStack.pop();
	}

	/**
	 * Check if an operator supplied is a valid and allowed operator
	 * 
	 * @param operator
	 * @return
	 */
	public static Boolean isOperator(Character operator) {
		if (!Character.isDigit(operator)) {
			Map<String, Operators> operatorMap = Operators.toMap();
			return operatorMap.keySet().contains(operator.toString());
		}
		return false;
	}

	public static Boolean isFunction(String token) {
		if (token != null && token.length() > 0) {
			Map<String, Functions> functionMap = Functions.toMap();
			return functionMap.keySet().contains(token.toString());
		}
		return false;
	}

	/**
	 * Check if its an open parenthesis
	 * 
	 * @param token
	 * @return
	 */
	public static Boolean isOpenParenthesis(Character token) {
		return token.equals('(');
	}

	/**
	 * Check if its a closed parenthesis
	 * 
	 * @param token
	 * @return
	 */
	public static Boolean isClosedParenthesis(Character token) {
		return token.equals(')');
	}

	/**
	 * Check the precedence status of two operators
	 * 
	 * @param operatorName
	 * @param operator
	 * @return
	 */
	public static Boolean precedence(String firstOperator, String secondOperator) {
		Map<String, Operators> operatorMap = Operators.toMap();
		Operators fOperators = operatorMap.get(firstOperator);
		Operators sOperators = operatorMap.get(secondOperator);
		if (fOperators != null && sOperators != null) {
			if (fOperators.getPrecedence() > sOperators.getPrecedence()) {
				return false;
			}
			if (fOperators.getPrecedence() < sOperators.getPrecedence()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if its a special character
	 * 
	 * @param token
	 * @return
	 */
	public static Boolean isSpecialCharacters(Character token) {
		return token.equals('!') || token.equals('.') || token.equals('&');
	}

	/**
	 * 
	 * Evaluate the functions and operations
	 * 
	 * @param functionName
	 * @param parameters
	 * @return
	 */
	private static BigDecimal evaluateOperation(String operationName, String... parameters) {
		Map<String, Functions> functionMap = Functions.toMap();
		Functions functions = functionMap.get(operationName);
		if (functions != null) {
			if (functions.equals(Functions.ABS)) {
				return new BigDecimal(Math.abs(Double.parseDouble(parameters[0])));
			}
			if (functions.equals(Functions.FACTORIAL)) {
				CalculableFunction calculableFunction = new Factorial();
				return new BigDecimal(calculableFunction.evaluate(parameters[0]).getResult());
			}
			if (functions.equals(Functions.COS)) {
				CalculableFunction calculableFunction = new Cosine();
				return new BigDecimal(calculableFunction.evaluate(parameters[0]).getResult());
			}
			if (functions.equals(Functions.SIN)) {
				CalculableFunction calculableFunction = new Sine();
				return new BigDecimal(calculableFunction.evaluate(parameters[0]).getResult());
			}
			if (functions.equals(Functions.TAN)) {
				CalculableFunction calculableFunction = new Tangent();
				return new BigDecimal(calculableFunction.evaluate(parameters[0]).getResult());
			}
		}
		Map<String, Operators> operatorMap = Operators.toMap();
		Operators operators = operatorMap.get(operationName);
		if (operators != null) {
			if (operators.equals(Operators.ADD)) {
				CalculableFunction calculableFunction = new Addition();
				return new BigDecimal(calculableFunction.evaluate(parameters).getResult());
			}
			if (operators.equals(Operators.DIVISION)) {
				CalculableFunction calculableFunction = new Division();
				return new BigDecimal(calculableFunction.evaluate(parameters).getResult());
			}
			if (operators.equals(Operators.MULTIPLY)) {
				CalculableFunction calculableFunction = new Multiplication();
				return new BigDecimal(calculableFunction.evaluate(parameters).getResult());
			}
			if (operators.equals(Operators.SUBTRACT)) {
				CalculableFunction calculableFunction = new Substraction();
				return new BigDecimal(calculableFunction.evaluate(parameters).getResult());
			}
		}
		return BigDecimal.ZERO;
	}

}
