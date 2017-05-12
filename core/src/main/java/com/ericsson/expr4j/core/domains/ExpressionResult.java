package com.ericsson.expr4j.core.domains;

import java.io.Serializable;

import com.ericsson.expr4j.core.exceptions.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author adeniranope
 *
 *	The result of the expression after processing
 *
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ExpressionResult implements Serializable {

	private static final long serialVersionUID = 5263835430294194514L;

	@JsonProperty(required = true)
	private final String result;
	
	@JsonProperty(value = "errorcode")
	private final ErrorCode errorCode;
	
	protected ExpressionResult(Builder builder){
		this.result = builder.result;
		this.errorCode = builder.errorCode;
	}
	
	public String getResult() {
		return result;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	public static Builder builder(){
		return new Builder();
	}
	
	public static final class Builder{
		private String result;
		private ErrorCode errorCode;
		
		public Builder result(String result){
			this.result = result;
			return this;
		}
		
		public Builder errorCode(ErrorCode errorCode){
			this.errorCode = errorCode;
			return this;
		}
		
		public ExpressionResult build(){
			return new ExpressionResult(this);
		}
		
	}
	
}
