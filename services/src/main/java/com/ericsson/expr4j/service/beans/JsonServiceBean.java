package com.ericsson.expr4j.service.beans;

import java.io.PrintStream;

import org.springframework.stereotype.Service;

import com.ericsson.expr4j.service.JsonService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author adeniranope
 *
 *         Service Implementation for reading and writing json strings
 *
 */

@Service(value = "jsonService")
public class JsonServiceBean implements JsonService {

	@Override
	public String toJson(Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception ex) {
			ex.printStackTrace(new PrintStream(System.out));
		}
		return null;
	}

	@Override
	public Object fromJson(String json,Class<?> classes) {
		try {
			return new ObjectMapper().readValue(json,classes);
		} catch (Exception ex) {
			ex.printStackTrace(new PrintStream(System.out));
		}
		return null;
	}

}
