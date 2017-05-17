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
import com.ericsson.expr4j.service.JsonService;
import com.ericsson.expr4j.service.beans.JsonServiceBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JsonServiceBeanTest {

	@Autowired
	@Qualifier(value="jsonService")
	JsonService jsonService;
	
	@Configuration
	static class JsonServiceTestConfiguration{
		
		@Bean
		public JsonService jsonService(){
			return new JsonServiceBean();
		}
	}
	
	@Test
	public void testJson(){
		ExpressionRequest expressionRequest = new ExpressionRequest();
		expressionRequest.setExpression("3+5+4+2+4+5+3+4+23");
	}
	
	
}
