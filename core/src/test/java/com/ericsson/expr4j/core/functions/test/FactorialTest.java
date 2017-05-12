package com.ericsson.expr4j.core.functions.test;

import org.junit.Test;

import com.ericsson.expr4j.core.domains.ExpressionResult;
import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.ericsson.expr4j.core.functions.Factorial;
import com.ericsson.expr4j.core.interfaces.Calculable;

public class FactorialTest {

	@Test
	public void testValidFactorial(){
		Calculable calculable = new Factorial();
		ExpressionResult expressionResult = calculable.evaluate("5");
		org.junit.Assert.assertEquals(expressionResult.getResult(), "120");
	}

	@Test
	public void testInvalidValue(){
		Calculable calculable = new Factorial();
		ExpressionResult expressionResult = calculable.evaluate("RT");
		org.junit.Assert.assertEquals(expressionResult.getErrorCode() != null,true);
		org.junit.Assert.assertEquals(expressionResult.getErrorCode(),ErrorCode.NUMBER_FORMAT_CONVERSION_ERROR);
	}
	
	@Test
	public void testInvalidParameters(){
		Calculable calculable = new Factorial();
		ExpressionResult expressionResult = calculable.evaluate("RT","25");
		org.junit.Assert.assertEquals(expressionResult.getErrorCode() != null,true);
		org.junit.Assert.assertEquals(expressionResult.getErrorCode(),ErrorCode.INVALID_FACTORIAL_PARAMETER);
	}
	
}
