package com.ericsson.expr4j.core.constants;

import java.util.HashMap;
import java.util.Map;

public enum Functions {

	SIN("sin"),COS("cos"),TAN("tan"),ABS("abs"),FACTORIAL("!");
	
	private final String functionName;
	
	Functions(String functionName){
		this.functionName = functionName;
	}
	
	public String getFunctionName() {
		return functionName;
	}
	
	public static Map<String,Functions> toMap(){
		Map<String,Functions> map = new HashMap<String, Functions>();
		for(Functions functions : Functions.values()){
			map.put(functions.getFunctionName(), functions);
		}
		return map;
	}
	
	
}
