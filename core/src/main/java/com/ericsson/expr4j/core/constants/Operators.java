package com.ericsson.expr4j.core.constants;

import java.util.HashMap;
import java.util.Map;

public enum Operators {

	ADD("+",2),SUBTRACT("-",1),MULTIPLY("*",3),DIVISION("/",4);
	
	private final String operatorSign;
	private final Integer precedence;
	
	Operators(String operatorSign,Integer precedence){
		this.operatorSign = operatorSign;
		this.precedence = precedence;
	}
	
	public Integer getPrecedence() {
		return precedence;
	}
	
	public String getOperatorSign() {
		return operatorSign;
	}
	
	public static Map<String,Operators> toMap(){
		Map<String,Operators> map = new HashMap<String, Operators>();
		for(Operators operators : Operators.values()){
			map.put(operators.getOperatorSign(), operators);
		}
		return map;
	}
	
}
