package com.ericsson.expr4j.service;

import com.ericsson.expr4j.core.constants.HttpMethod;

public interface JsonHttpService {

	String send(HttpMethod httpMethod, String url, Object request) throws Exception;
	
	
	
}
