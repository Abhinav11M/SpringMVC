package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//@JsonIgnoreProperties({"value1", "value2"}) // Static filtering of fields 
@JsonFilter("SomeBeanFilter")
public class SomeBean {
//	@JsonIgnore
	private String field1;
	private String field2;
	private String field3;
}
