package com.ericsson.expr4j.core.functions;

import com.ericsson.expr4j.core.domains.ExpressionResult;
import com.ericsson.expr4j.core.domains.ExpressionResult.Builder;
import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.ericsson.expr4j.core.interfaces.CalculableFunction;
import com.google.common.base.Preconditions;

/**
 * 
 * @author adeniranope
 *
 * Calculates the sine of a number
 *
 */

public class Sine implements CalculableFunction{

	@Override
	public ExpressionResult evaluate(String... parameters) {
		Preconditions.checkNotNull(parameters, "Parameter cannot be NULL");
		ExpressionResult.Builder expressionResultBuilder = this.validate(parameters);
		ExpressionResult expressionResult = expressionResultBuilder.build();
		if (expressionResult.getErrorCode() == null) {
			expressionResultBuilder.result(String.valueOf(Math.sin(Double.parseDouble(parameters[0]))));
			return expressionResultBuilder.build();
		}
		return expressionResult;
	}

	@Override
	public Builder validate(String... parameters) {
		ExpressionResult.Builder expressionResultBuilder = ExpressionResult.builder();
		if (parameters.length == 1) {
			try {
				Integer.parseInt(parameters[0]);
			} catch (NumberFormatException ne) {
				expressionResultBuilder.errorCode(ErrorCode.NUMBER_FORMAT_CONVERSION_ERROR);
			}
		} else {
			expressionResultBuilder.errorCode(ErrorCode.INVALID_PARAMETER_LENGTH);
		}
		return expressionResultBuilder;

	}

}
