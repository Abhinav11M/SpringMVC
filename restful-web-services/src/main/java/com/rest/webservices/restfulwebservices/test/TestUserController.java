package com.rest.webservices.restfulwebservices.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestUserController {
	
	@GetMapping("/allTestUsers")
	List<TestUser> getAllUsers() {
		List<TestUser> list = new ArrayList<>();
		list.add(new TestUser(1, "Abhinav"));
		list.add(new TestUser(2, "Kumar"));
		list.add(new TestUser(3, "TestUser"));
		
		return list;
	}
	
	@GetMapping("/testUser/{id}")
	EntityModel<TestUser> getUser(@PathVariable int id) {
		TestUser u = new TestUser(id, "Abhinav");
		
		Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers()).withRel("all-users");
		
		return new EntityModel<TestUser>(u, new Link[] {link});
	}
}
