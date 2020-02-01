package com.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {
	
	private static List<User> users = new ArrayList<>();
	
	private static int idCount = 4;
	
	static {
		Collections.addAll(users, 
				(new User[] {
						new User(1,"Abhinav", new Date(), null),
						new User(2,"Mohit", new Date(), null),
						new User(3,"Pandit", new Date(), null),
						new User(4,"Abhijeet", new Date(), null)
						}
				));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		Optional<User> found = users.stream().filter(x -> x.getId() == user.getId()).findAny();
		if(found.isPresent()) {
			return found.get();
		}
		
		// If user has Id
		if(user.getId() != null) {
			users.add(user);
		}
		else {
			user.setId(++idCount);
			users.add(user);
		}

		return user;
	}
	
	public User findUser(int id) {
		Optional<User> found = users.stream().filter(x -> x.getId() == id).findAny();
		return found.isPresent() ? found.get() : null;
	}
	
	//Delete a user
	public User deleteUser(int id) {
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User user = it.next();
			if(user.getId() == id) {
				it.remove();
				return user;
			}
		}
		return null;
	}
}


















