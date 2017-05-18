package com.ericsson.expr4j.api.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ericsson.expr4j.api.controllers")
public class Application {

	public static void main(String[] args){
		SpringApplication.run(Application.class,args);
	}
	
}
