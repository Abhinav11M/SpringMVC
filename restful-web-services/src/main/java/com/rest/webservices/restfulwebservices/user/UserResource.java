package com.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDAOService userDaoService;
	
	// Retrieve all users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userDaoService.findAll();
	}
	
	// Get specifcic user
	@GetMapping("/users/{id}")
	public User getSpecificUser(@PathVariable int id) {
		User user = userDaoService.findUser(id);
		if(user == null) {
			throw new UserNotFoundException(String.format("Id: %s not found", id));
		}
		
		return user;
	}
	
	// Create a user
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) { // The passed json in mapped to User
		User savedUser = userDaoService.save(user);
		
		// Return the status code and the uri of the created user.
		// URI => /users/{id} => retUser.getId()
		
		// We need to create /users/{4} kindof uri without hardcoding
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		// Status code CREATED(201), that's why used .created()
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
//	public EntityModel<User> deleteSpecificUser(@PathVariable int id) {
	public User deleteSpecificUser(@PathVariable int id) {
		User user = userDaoService.deleteUser(id);

		if(user == null) {
			throw new UserNotFoundException(String.format("Id: %s not found", id));
		}
		
		// HATEOAS
//		Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
//				.methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users");
		
		
//		return new EntityModel<User>(user, new Link[] {link});
		return user;
	}
}
