package com.ericsson.expr4j.service.beans.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ericsson.expr4j.core.domains.Expression;
import com.ericsson.expr4j.core.domains.ExpressionResult;
import com.ericsson.expr4j.service.Expression4JService;
import com.ericsson.expr4j.service.beans.Expression4JServiceBean;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class Expression4JServiceBeanTest {

	@Autowired
	Expression4JService expression4jService;
	
	
	@Configuration
	static class Expression4JServiceTestConfiguration{
		
		@Bean
		public Expression4JService expression4JService(){
			return new Expression4JServiceBean();
		}
	}

	@Test
	public void testEvaluation(){
		ExpressionResult expressionResult = this.expression4jService.evaluateExpression(Expression.builder()
				.expression("54*(34+90)/10")
				.build());
		Assert.assertEquals(expressionResult.getResult(),"669.60000");
	}
	
}
