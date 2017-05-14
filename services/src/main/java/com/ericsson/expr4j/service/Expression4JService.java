package com.ericsson.expr4j.service;

import com.ericsson.expr4j.core.domains.Expression;
import com.ericsson.expr4j.core.domains.ExpressionResult;

public interface Expression4JService {

	ExpressionResult evaluateExpression(Expression expression);
	
}
