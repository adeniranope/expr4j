package com.ericsson.expr4j.core.domains;

import java.io.Serializable;

import com.ericsson.expr4j.core.functions.Evaluation;
import com.ericsson.expr4j.core.validations.Validations;

/**
 * 
 * @author adeniranope
 *
 * Wrapper class for the expression
 *
 */
public final class Expression implements Serializable {

	private static final long serialVersionUID = -3705362688857985343L;

	private final String expression;

	protected Expression(Builder builder) {
		this.expression = builder.expression;
	}

	public static Builder builder() {
		return new Builder();
	}

	public String getExpression() {
		return expression;
	}

	public String evaluate() {
		return Evaluation.evaluate(expression);
	}

	public static final class Builder {

		private String expression;

		public Builder expression(String expression) {
			this.expression = expression;
			return this;
		}

		public Expression build() {
			return Validations.validate(new Expression(this));
		}

	}

	public String toString() {
		return this.expression;
	}

}
