package com.ericsson.expr4j.core.domains;

import java.io.Serializable;

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
	
	protected ExpressionResult(Builder builder){
		this.result = builder.result;
	}
	
	public String getResult() {
		return result;
	}
	
	public static final class Builder{
		private String result;
		
		public Builder result(String result){
			this.result = result;
			return this;
		}
		
		public ExpressionResult build(){
			return new ExpressionResult(this);
		}
		
	}
	
}
