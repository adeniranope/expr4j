package com.ericsson.expr4j.api.controllers.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ericsson.expr4j.api.controllers.JsonAPIController;
import com.ericsson.expr4j.service.Expression4JService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {JsonAPIController.class,})
@ComponentScan(basePackages = "com.ericsson.expr4j.api.controllers")
public class JsonAPIControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	Expression4JService expression4JService;
	
	@Before
	public void setup(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
		
	@Test
	public void testExpressionWithJson(){
/*		try {
			Mockito.when(this.expression4JService.evaluateExpression(Mockito.any(Expression.class)))
			.thenReturn(ExpressionResult.builder().result("450").build());
			mockMvc.perform(MockMvcRequestBuilders.post("expr4j/evaluate")
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content("{'expression':'5! + abs(6-7*7) / 9'}"))
			.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/	}
	
}
