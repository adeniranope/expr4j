package com.ericsson.expr4j.core.functions.test;

import org.junit.Assert;
import org.junit.Test;

import com.ericsson.expr4j.core.domains.ExpressionResult;
import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.ericsson.expr4j.core.functions.Tangent;
import com.ericsson.expr4j.core.interfaces.CalculableFunction;

/**
 * 
 * @author adeniranope
 *
 * Test class for the tangent class
 *
 */

public class TangentTest {

	@Test
	public void testTangent(){
		CalculableFunction calculable = new Tangent();
		ExpressionResult expressionResult = calculable.evaluate("30");
		Assert.assertEquals(expressionResult.getResult(),"-6.405331196646276");
	}

	@Test
	public void testInvalidValue(){
		CalculableFunction calculable = new Tangent();
		ExpressionResult expressionResult = calculable.evaluate("KKY");
		Assert.assertEquals(expressionResult.getErrorCode() != null,true);
		Assert.assertEquals(expressionResult.getErrorCode(),ErrorCode.NUMBER_FORMAT_CONVERSION_ERROR);
	}
	
	@Test
	public void testInvalidParameter(){
		CalculableFunction calculable = new Tangent();
		ExpressionResult expressionResult = calculable.evaluate();
		Assert.assertEquals(expressionResult.getErrorCode() != null,true);
		Assert.assertEquals(expressionResult.getErrorCode(),ErrorCode.INVALID_PARAMETER_LENGTH);
	}
	
	
}
