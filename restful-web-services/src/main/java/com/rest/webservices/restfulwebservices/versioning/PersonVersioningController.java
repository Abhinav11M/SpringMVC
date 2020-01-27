package com.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	/* URI Versioning */
	@GetMapping("v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Abhinav");
	}

	@GetMapping("v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Abhinav", "Kumar"));
	}
	
	/* Param based versioning => Request param versioning */
	// http://localhost:8080/person/param?version=1
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Abhinav");
	}
	
	//http://localhost:8080/person/param?version=2
	@GetMapping(path = "/person/param", params = "version=2") // value is a alias of path
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Abhinav", "Kumar"));
	}
	
	/* Header based versioning => Request header versioning*/
	// http://localhost:8080/person/header + Header X-API-VERSION : 1
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Abhinav");
	}
	
	// http://localhost:8080/person/header + Header X-API-VERSION : 2
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Abhinav", "Kumar"));
	}
	
	/* Versioning using producers => MIME versioning*/
	// http://localhost:8080/person/produces; accept (header) : application/com.rest.app-v1+json
	@GetMapping(value = "/person/produces", produces = "application/com.rest.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Abhinav");
	}
	
	
	// http://localhost:8080/person/produces; accept (header) : application/com.rest.app-v2+json
	@GetMapping(value = "/person/produces", produces = "application/com.rest.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Abhinav", "Kumar"));
	}
}
