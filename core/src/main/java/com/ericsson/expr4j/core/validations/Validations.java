package com.ericsson.expr4j.core.validations;

import org.springframework.util.StringUtils;

import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.ericsson.expr4j.core.exceptions.Expression4JException;

public enum Validations {

	INSTANCE;

	public static <T> T validate(final T value) {
		if (value == null) {
			return null;
		}
		if (StringUtils.countOccurrencesOf(value.toString(), "(") == StringUtils.countOccurrencesOf(value.toString(),
				")")) {
			return value;
		} else {
			throw new Expression4JException(ErrorCode.INVALID_BRACKET_COUNTS,
					"Opening and Closing Brackets does not match in the expression");
		}
	}
	
}
