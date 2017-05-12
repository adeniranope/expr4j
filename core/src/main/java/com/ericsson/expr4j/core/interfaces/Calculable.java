/**
 * 
 */
package com.ericsson.expr4j.core.interfaces;

import com.ericsson.expr4j.core.exceptions.Expression4JException;

/**
 * @author adeniranope
 *
 */
public interface Calculable {

	public String evaluate(String... parameters) throws Expression4JException;
	
	public String validate(String... parameters) throws Expression4JException;
	
}
