package com.ericsson.expr4j.core.functions.test;

import org.junit.Assert;
import org.junit.Test;

import com.ericsson.expr4j.core.domains.ExpressionResult;
import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.ericsson.expr4j.core.functions.Cosine;
import com.ericsson.expr4j.core.interfaces.CalculableFunction;

/**
 * 
 * @author adeniranope
 *
 * The test class of the cosine function
 *
 */

public class CosineTest {

	@Test
	public void testCosine(){
		CalculableFunction calculable = new Cosine();
		ExpressionResult expressionResult = calculable.evaluate("30");
		Assert.assertEquals(expressionResult.getResult(),"0.15425144988758405");
	}

	@Test
	public void testInvalidValue(){
		CalculableFunction calculable = new Cosine();
		ExpressionResult expressionResult = calculable.evaluate("KKY");
		Assert.assertEquals(expressionResult.getErrorCode() != null,true);
		Assert.assertEquals(expressionResult.getErrorCode(),ErrorCode.NUMBER_FORMAT_CONVERSION_ERROR);
	}
	
	@Test
	public void testInvalidParameter(){
		CalculableFunction calculable = new Cosine();
		ExpressionResult expressionResult = calculable.evaluate();
		Assert.assertEquals(expressionResult.getErrorCode() != null,true);
		Assert.assertEquals(expressionResult.getErrorCode(),ErrorCode.INVALID_PARAMETER_LENGTH);
	}
	
	
}
