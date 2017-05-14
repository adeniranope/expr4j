/**
 * 
 */
package com.ericsson.expr4j.core.interfaces;

import com.ericsson.expr4j.core.domains.ExpressionResult;

/**
 * @author adeniranope
 *
 */
public interface CalculableFunction {

	public ExpressionResult evaluate(String... parameters);
	
	public ExpressionResult.Builder validate(String... parameters);
	
}
