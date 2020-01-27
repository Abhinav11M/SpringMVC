package com.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

//	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World Bean");
	}

	@GetMapping(path = "/hello-world-bean/args/{message}")
	public HelloWorldBean helloWorldBeanWithArgs(@PathVariable String message) {
		return new HelloWorldBean("Hello " + message);
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) { 
		// Pass locale from the request header, defalt is US (Set in the locale resolver)
		return messageSource.getMessage("good.morning.message", null,  locale);
	}

}
