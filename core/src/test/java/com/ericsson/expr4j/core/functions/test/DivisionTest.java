package com.ericsson.expr4j.core.functions.test;

import org.junit.Assert;
import org.junit.Test;

import com.ericsson.expr4j.core.domains.ExpressionResult;
import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.ericsson.expr4j.core.functions.Division;
import com.ericsson.expr4j.core.interfaces.Calculable;

/**
 * 
 * @author adeniranope
 *
 * The test class of a division mathematical function
 *
 */

public class DivisionTest {

	@Test
	public void testDivision(){
		Calculable calculable = new Division();
		ExpressionResult expressionResult = calculable.evaluate("12","3");
		Assert.assertEquals(expressionResult.getResult(),"4");
	}

	@Test
	public void testInvalidValue(){
		Calculable calculable = new Division();
		ExpressionResult expressionResult = calculable.evaluate("2","HY");
		Assert.assertEquals(expressionResult.getErrorCode() != null,true);
		Assert.assertEquals(expressionResult.getErrorCode(),ErrorCode.NUMBER_FORMAT_CONVERSION_ERROR);
	}
	
	@Test
	public void testInvalidParameter(){
		Calculable calculable = new Division();
		ExpressionResult expressionResult = calculable.evaluate();
		Assert.assertEquals(expressionResult.getErrorCode() != null,true);
		Assert.assertEquals(expressionResult.getErrorCode(),ErrorCode.INVALID_PARAMETER_LENGTH);
	}
	
	
}
