package com.ericsson.expr4j.core.functions.test;

import org.junit.Assert;
import org.junit.Test;

import com.ericsson.expr4j.core.domains.ExpressionResult;
import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.ericsson.expr4j.core.functions.Sine;
import com.ericsson.expr4j.core.interfaces.Calculable;

public class SineTest {

	@Test
	public void testSine(){
		Calculable calculable = new Sine();
		ExpressionResult expressionResult = calculable.evaluate("30");
		Assert.assertEquals(expressionResult.getResult(),"-0.9880316240928618");
	}

	@Test
	public void testInvalidValue(){
		Calculable calculable = new Sine();
		ExpressionResult expressionResult = calculable.evaluate("KKY");
		Assert.assertEquals(expressionResult.getErrorCode() != null,true);
		Assert.assertEquals(expressionResult.getErrorCode(),ErrorCode.NUMBER_FORMAT_CONVERSION_ERROR);
	}
	
	@Test
	public void testInvalidParameter(){
		Calculable calculable = new Sine();
		ExpressionResult expressionResult = calculable.evaluate();
		Assert.assertEquals(expressionResult.getErrorCode() != null,true);
		Assert.assertEquals(expressionResult.getErrorCode(),ErrorCode.INVALID_PARAMETER_LENGTH);
	}
	
	
}
