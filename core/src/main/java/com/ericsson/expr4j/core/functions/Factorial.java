package com.ericsson.expr4j.core.functions;

import com.ericsson.expr4j.core.domains.ExpressionResult;
import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.ericsson.expr4j.core.interfaces.Calculable;
import com.google.common.base.Preconditions;

public final class Factorial implements Calculable {

	private Integer factorial(Integer number) {
		if (number == 0) {
			return 1;
		} else {
			return number * factorial(number - 1);
		}
	}

	@Override
	public ExpressionResult evaluate(String... parameters) {
		Preconditions.checkNotNull(parameters, "Parameter cannot be NULL");
		ExpressionResult.Builder expressionResultBuilder = this.validate(parameters);
		ExpressionResult expressionResult = expressionResultBuilder.build();
		if (expressionResult.getErrorCode() == null) {
			Integer factorialNumber = Integer.parseInt(parameters[0]);
			expressionResultBuilder.result(String.valueOf(this.factorial(factorialNumber)));
			return expressionResultBuilder.build();
		}
		return expressionResult;
	}

	@Override
	public ExpressionResult.Builder validate(String... parameters) {
		ExpressionResult.Builder expressionResultBuilder = ExpressionResult.builder();
		if (parameters.length == 1) {
			try {
				Integer.parseInt(parameters[0]);
			} catch (NumberFormatException ne) {
				expressionResultBuilder.errorCode(ErrorCode.NUMBER_FORMAT_CONVERSION_ERROR);
			}
		} else {
			expressionResultBuilder.errorCode(ErrorCode.INVALID_FACTORIAL_PARAMETER);
		}
		return expressionResultBuilder;
	}

}
