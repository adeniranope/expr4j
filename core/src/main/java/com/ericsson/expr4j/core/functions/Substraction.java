package com.ericsson.expr4j.core.functions;

import java.math.BigDecimal;

import com.ericsson.expr4j.core.domains.ExpressionResult;
import com.ericsson.expr4j.core.domains.ExpressionResult.Builder;
import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.ericsson.expr4j.core.interfaces.Calculable;
import com.google.common.base.Preconditions;

public final class Substraction implements Calculable {

	@Override
	public ExpressionResult evaluate(String... parameters) {
		ExpressionResult.Builder expressionResultBuilder = ExpressionResult.builder();
		try {
			Preconditions.checkNotNull(parameters, "Parameters cannot be NULL");
			expressionResultBuilder = this.validate(parameters);
			ExpressionResult validationExpressionResult = expressionResultBuilder.build();
			if (validationExpressionResult.getErrorCode() == null) {
				BigDecimal bigDecimal = BigDecimal.ZERO;
				int count = 0;
				for (String param : parameters) {
					if (count == 0) {
						bigDecimal = bigDecimal.add(new BigDecimal(param));
						count++;
					} else {
						bigDecimal = bigDecimal.subtract(new BigDecimal(param));
					}
				}
				expressionResultBuilder.result(bigDecimal.toString());
			}
		} catch (Exception ex) {
			expressionResultBuilder.errorCode(ErrorCode.INTERNAL_ERROR);
		}
		return expressionResultBuilder.build();
	}

	@Override
	public Builder validate(String... parameters) {
		ExpressionResult.Builder exBuilder = ExpressionResult.builder();
		if (parameters.length > 0) {
			for (String param : parameters) {
				try {
					new BigDecimal(param);
				} catch (Exception ex) {
					exBuilder.errorCode(ErrorCode.NUMBER_FORMAT_CONVERSION_ERROR);
					return exBuilder;
				}
			}
		} else {
			exBuilder.errorCode(ErrorCode.INVALID_PARAMETER);
		}
		return exBuilder;
	}

}
