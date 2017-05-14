package com.ericsson.expr4j.core.validations;

import org.springframework.util.StringUtils;

import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.ericsson.expr4j.core.exceptions.Expression4JException;

/**
 * 
 * @author adeniranope
 *
 *         Validation of the expression during construction
 *
 */

public enum Validations {

	INSTANCE;

	/**
	 * 
	 * Validate the expression, counting the numbers of brackets, perform
	 * operator checks, and digit checks
	 * 
	 * @param value
	 * @return
	 */
	public static <T> T validate(final T value) {
		if (value == null) {
			throw new Expression4JException(ErrorCode.INVALID_EXPRESSION,
					"Invalid Expression entered");
		}
		Integer a = StringUtils.countOccurrencesOf(value.toString(), "(");
		Integer b = StringUtils.countOccurrencesOf(value.toString(), ")");
		if (a == b) {
			// check for allowed operators
			return value;
		} else {
			throw new Expression4JException(ErrorCode.INVALID_BRACKET_COUNTS,
					"Opening and Closing Brackets does not match in the expression");
		}
	}

}
