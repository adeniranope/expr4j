package com.ericsson.expr4j.core.functions.test;

import org.junit.Assert;
import org.junit.Test;

import com.ericsson.expr4j.core.functions.Evaluation;

/**
 * 
 * @author adeniranope
 *
 *         Test class for evaluation of expressions using ShuntingYard algorithm
 *
 */

public class EvaluationTest {

	@Test
	public void testPostFix() {
		String exp = Evaluation.evaluate("100*2+12");
		Assert.assertEquals("212", exp);
		exp = Evaluation.evaluate("100*(2+12)/14");
		Assert.assertEquals("100.00000", exp);
		exp = Evaluation.evaluate("5!+abs(7*7)/9.8");
		Assert.assertEquals("125.00000", exp);
		exp = Evaluation.evaluate("5!+abs(6-7*7)/9");
		Assert.assertEquals("124.77778", exp);
		exp = Evaluation.evaluate("10+(2*6)");
		Assert.assertEquals("22", exp);
	}

}
