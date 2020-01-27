package com.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {
	
	public HelloWorldBean(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	private String message;
}
