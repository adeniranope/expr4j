package com.ericsson.expr4j.core.functions.test;

import org.junit.Assert;
import org.junit.Test;

import com.ericsson.expr4j.core.domains.ExpressionResult;
import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.ericsson.expr4j.core.functions.Substraction;
import com.ericsson.expr4j.core.interfaces.Calculable;

/**
 * 
 * @author adeniranope
 *
 * The test class of the substraction function
 *
 */

public class SubstractionTest {

	@Test
	public void testSubstraction(){
		Calculable calculable = new Substraction();
		ExpressionResult expressionResult = calculable.evaluate("2","3","5","7");
		Assert.assertEquals(expressionResult.getResult(),"-13");
	}

	@Test
	public void testInvalidValue(){
		Calculable calculable = new Substraction();
		ExpressionResult expressionResult = calculable.evaluate("2","HY","5","7");
		Assert.assertEquals(expressionResult.getErrorCode() != null,true);
		Assert.assertEquals(expressionResult.getErrorCode(),ErrorCode.NUMBER_FORMAT_CONVERSION_ERROR);
	}
	
	@Test
	public void testInvalidParameter(){
		Calculable calculable = new Substraction();
		ExpressionResult expressionResult = calculable.evaluate();
		Assert.assertEquals(expressionResult.getErrorCode() != null,true);
		Assert.assertEquals(expressionResult.getErrorCode(),ErrorCode.INVALID_PARAMETER);
	}
	
	
}
