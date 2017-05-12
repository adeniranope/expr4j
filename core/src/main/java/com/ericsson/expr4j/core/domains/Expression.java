package com.ericsson.expr4j.core.domains;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author adeniranope
 *
 * Wrapper class for the expression
 *
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Expression implements Serializable {

	private static final long serialVersionUID = -3705362688857985343L;

	@JsonProperty(required = true)
	private final String expression;
	
	protected Expression(Builder builder){
		this.expression = builder.expression;
	}
	
	public static Builder builder(){
		return new Builder();
	}
	
	public String getExpression() {
		return expression;
	}
	
	public static final class Builder{
		
		private String expression;
		
		public Builder expression(String expression){
			this.expression = expression;
			return this;
		}
		
		public Expression build(){
			return new Expression(this);
		}
		
	}
	
}
