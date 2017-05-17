package com.ericsson.expr4j.service.beans;

import org.springframework.stereotype.Service;

import com.ericsson.expr4j.service.Expression4JConfigurationService;

@Service(value = "expression4JConfigurationService")
public class Expression4JConfigurationServiceBean implements Expression4JConfigurationService {

	String jsonApiUrl;
	
	public String getJsonApiUrl() {
		return jsonApiUrl;
	}
	
	public void setJsonApiUrl(String jsonApiUrl) {
		this.jsonApiUrl = jsonApiUrl;
	}
	
	@Override
	public String jsonApiUrl() {
		return this.jsonApiUrl;
	}

}
