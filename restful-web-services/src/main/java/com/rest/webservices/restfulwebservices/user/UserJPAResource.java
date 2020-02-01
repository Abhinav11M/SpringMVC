package com.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	// Retrieve all users
	@GetMapping("jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	// Get specifcic user
	@GetMapping("jpa/users/{id}")
	public User getSpecificUser(@PathVariable int id) {
		return userRepository.findById(id).orElseThrow(RuntimeException::new);
	}
	
	// Create a user
	@PostMapping("jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) { // The passed json in mapped to User
		User savedUser = userRepository.save(user);
		
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
	
	@DeleteMapping("jpa/users/{id}")
	public void deleteSpecificUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	// Get all the posts for a specific user
	@GetMapping("jpa/users/{id}/posts")
	List<Post> getPostByUserId(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("Could not find user with user id : " + id);
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping("jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		// Find the user with Id
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException(String.format("User %d not found in database", id));
		}
		
		User userRes = user.get();
		
		// Set the user in the post object
		post.setUser(userRes);
		
		// Save post in the database
		postRepository.save(post);
		
		URI response = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(userRes.getId()).toUri();
		
		return ResponseEntity.created(response).build();
	}
}
