package com.ericsson.expr4j.service.beans.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ericsson.expr4j.api.domains.ExpressionRequest;
import com.ericsson.expr4j.core.constants.HttpMethod;
import com.ericsson.expr4j.service.JsonHttpService;
import com.ericsson.expr4j.service.JsonService;
import com.ericsson.expr4j.service.beans.JsonHttpServiceBean;
import com.ericsson.expr4j.service.beans.JsonServiceBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JsonHttpServiceBeanTest {

	@Autowired
	@Qualifier(value="jsonHttpService")
	JsonHttpService jsonHttpService;
	
	@Autowired
	@Qualifier(value = "jsonService")
	JsonService jsonService;
	
	@Configuration
	static class JsonHttpServiceTestConfiguration{
		
		@Bean
		public JsonHttpService jsonHttpService(){
			return new JsonHttpServiceBean();
		}
		
		@Bean
		public JsonService jsonService(){
			return new JsonServiceBean();
		}
		
	}
	
	@Test
	public void testJsonHttp(){
		ExpressionRequest expressionRequest = new ExpressionRequest();
		expressionRequest.setExpression("1+2+2+3+4");
		try {
			this.jsonHttpService.send(HttpMethod.POST,"http://localhost:8202/",expressionRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
