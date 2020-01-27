package com.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	// No filtering
	@GetMapping(path = "/no-filtering")
	public SomeBean retriveBean() {
		return new SomeBean("value1", "value2", "value3");
	}
	
	// Send only field1 and field2
	@GetMapping(path="/filtering")
	public MappingJacksonValue retrieveBeanWithFilter() {
		SomeBean bean = new SomeBean("value1", "value2", "value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		
		// This filter has to be set in the SomeBean class
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter); 

		MappingJacksonValue mapping = new MappingJacksonValue(bean);
		mapping.setFilters(filterProvider);
		
		return mapping;
	}
	
	@GetMapping(path = "/filtering-list")
	public MappingJacksonValue retrieveSomeBeanList() {
		
		List<SomeBean> beans = Arrays.asList(new SomeBean("val1", "val2", "val3"), new SomeBean("val11", "val22", "val33"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(beans);
		mapping.setFilters(filters);
		
		return mapping;
	}
}
