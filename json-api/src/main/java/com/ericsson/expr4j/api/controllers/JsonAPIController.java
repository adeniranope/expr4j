package com.ericsson.expr4j.api.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ericsson.expr4j.api.domains.ExpressionRequest;
import com.ericsson.expr4j.api.domains.ExpressionResponse;
import com.ericsson.expr4j.core.domains.Expression;
import com.ericsson.expr4j.core.domains.ExpressionResult;
import com.ericsson.expr4j.core.exceptions.Expression4JException;
import com.ericsson.expr4j.service.Expression4JService;
import com.google.common.base.Preconditions;

/**
 * 
 * @author adeniranope
 *
 * The JSON API interface for expression service
 *
 */

@EnableWebMvc
@RestController
public class JsonAPIController {

	@Autowired
	Expression4JService expression4JService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,value = "expr4j/evaluate")
	public ExpressionResponse expression(@RequestBody ExpressionRequest expressionRequest, HttpServletRequest request,
			HttpServletResponse response) {
		ExpressionResponse expressionResponse = new ExpressionResponse();
		try {
			Preconditions.checkNotNull(expressionRequest, "No request found");
			Expression expression = Expression.builder()
					.expression(expressionRequest.getExpression())
					.build();
			ExpressionResult expressionResult = this.expression4JService.evaluateExpression(expression);
			Preconditions.checkNotNull(expressionResult);
			expressionResponse.setResult(expressionResult.getResult());
			expressionResponse.setErrorCode(expressionResult.getErrorCode());
		} catch (Expression4JException ex) {
			expressionResponse.setErrorCode(ex.getErrorCode());
		}
		return expressionResponse;
	}

}
