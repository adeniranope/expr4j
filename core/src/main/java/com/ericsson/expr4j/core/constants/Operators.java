package com.ericsson.expr4j.core.constants;

public enum Operators {

	ADD("+"),SUBTRACT("-"),MULTIPLY("*"),DIVISION("/"),FACTORIAL("!"),SIN("sin"),COS("cos"),TAN("tan");
	
	private final String operatorSign;
	
	Operators(String operatorSign){
		this.operatorSign = operatorSign;
	}
	
	public String getOperatorSign() {
		return operatorSign;
	}
	
}
