package com.ericsson.expr4j.api.domains;

import java.io.Serializable;

import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpressionResponse implements Serializable{

	private static final long serialVersionUID = -1789157492995346097L;
	
	@JsonProperty(required = true)
	private String result;
	
	private ErrorCode errorCode;
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
}
