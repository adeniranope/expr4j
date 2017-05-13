package com.ericsson.expr4j.core.domains.test;

import org.junit.Assert;
import org.junit.Test;

import com.ericsson.expr4j.core.domains.Expression;
import com.ericsson.expr4j.core.exceptions.Expression4JException;

public class ExpressionTest {

	/**
	 * 
	 * Constructs a new mathematical expression 
	 * 
	 */
	
	@Test
	public void testExpression() {
		Expression expression = Expression.builder().expression("(2*3)+(9*8)").build();
		Assert.assertNotNull(expression);
		Assert.assertEquals(expression.getExpression(), "(2*3)+(9*8)");
	}

	/**
	 * 
	 * @throws Expression4JException
	 * 
	 * Check opening and closing brackets
	 * 
	 */
	@Test(expected = Expression4JException.class)
	public void testExpressionBrackets() throws Expression4JException {
		try{
		Expression expression = Expression.builder().expression("(2*3+(9*8)").build();
		Assert.assertEquals(expression.getExpression(),"(2*3+(9*8)");
		}catch(Expression4JException ex){
			throw ex;
		}
	}

}
