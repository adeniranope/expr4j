package com.ericsson.expr4j.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.ericsson.expr4j.api.domains.ExpressionRequest;
import com.ericsson.expr4j.api.domains.ExpressionResponse;

@WebService(targetNamespace = "http://www.ericsson.com/expr4j/ws",name = "ExpressionWebService")
@SOAPBinding(style = Style.RPC)
public interface ExpressionWebService {

	@WebMethod
	ExpressionResponse processExpression(ExpressionRequest expressionRequest);
	
}
