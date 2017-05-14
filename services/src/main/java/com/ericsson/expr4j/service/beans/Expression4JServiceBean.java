package com.ericsson.expr4j.service.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ericsson.expr4j.core.domains.Expression;
import com.ericsson.expr4j.core.domains.ExpressionResult;
import com.ericsson.expr4j.service.Expression4JService;
import com.google.common.base.Preconditions;

@Service
public class Expression4JServiceBean implements Expression4JService {

	private Logger logger = LoggerFactory.getLogger(Expression4JServiceBean.class);

	@Override
	public ExpressionResult evaluateExpression(Expression expression) {
		Preconditions.checkNotNull(expression);
		logger.debug(">> executing the expression");
		return ExpressionResult.builder().result(expression.evaluate()).build();
	}

}
