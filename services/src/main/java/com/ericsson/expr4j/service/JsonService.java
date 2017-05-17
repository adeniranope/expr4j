package com.ericsson.expr4j.service;

public interface JsonService {

	public String toJson(Object object);
	
	public Object fromJson(String json);
}
