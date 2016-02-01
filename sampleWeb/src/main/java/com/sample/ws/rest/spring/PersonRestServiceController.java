package com.sample.ws.rest.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.entity.Person;
import com.sample.service.PersonService;

@Controller
/*@RequestMapping(value = "/spring")*/
public class PersonRestServiceController {

	@Autowired
	PersonService service;
	
	@RequestMapping(value = "/api/person", method = RequestMethod.POST)
	@ResponseBody
	public Person add(@RequestBody Person dto) {
System.out.println("Saving....");
		service.save(dto); 

		return dto;

	}

	@RequestMapping(value = "/api/person/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Person findById(@PathVariable("id") Long id) throws Exception {

		System.out.println("Finding....");
		Person found = service.get(id);

		return found;

	}
	
	@RequestMapping(value = "/api/persons", method = RequestMethod.GET)
	@ResponseBody
	public List<Person> findAll() throws Exception {

		System.out.println("Finding all...."+service);
		//List<Person> lst=service.getAll();
		//System.out.println("Returning all...."+lst);
		
		Integer count= new Integer(1);
		for(int i=0;i<100;i++)
		{
			count++;
		}
		System.out.println(count);
		return  new ArrayList();

	}
	  


}
