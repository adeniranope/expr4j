package com.ericsson.expr4j.webservice.beans;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ericsson.expr4j.api.domains.ExpressionRequest;
import com.ericsson.expr4j.api.domains.ExpressionResponse;
import com.ericsson.expr4j.core.constants.HttpMethod;
import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.ericsson.expr4j.service.Expression4JConfigurationService;
import com.ericsson.expr4j.service.JsonHttpService;
import com.ericsson.expr4j.service.JsonService;
import com.ericsson.expr4j.webservice.ExpressionWebService;
import com.google.common.base.Preconditions;

@WebService(endpointInterface = "com.ericsson.expr4j.ws.Expression")
public class ExpressionWebServiceBean implements ExpressionWebService {

	@Autowired
	@Qualifier(value = "jsonHttpService")
	JsonHttpService jsonHttpService;

	@Autowired
	@Qualifier(value = "jsonService")
	JsonService jsonService;

	@Autowired
	@Qualifier(value = "expression4JConfigurationService")
	Expression4JConfigurationService expression4JConfigurationService;

	@WebMethod
	@Override
	public ExpressionResponse processExpression(ExpressionRequest expressionRequest) {
		try {
			Preconditions.checkNotNull(expressionRequest);
			
			String jsonResponse = this.jsonHttpService.send(HttpMethod.POST,
					this.expression4JConfigurationService.jsonApiUrl(), expressionRequest);

			ExpressionResponse expressionResponse = (ExpressionResponse)this.jsonService.fromJson(jsonResponse,ExpressionResponse.class);
			return expressionResponse;			
		} catch (Exception ex) {
			ExpressionResponse expressionResponse = new ExpressionResponse();
			expressionResponse.setErrorCode(ErrorCode.INTERNAL_ERROR);
			return expressionResponse;
		}
	}

}