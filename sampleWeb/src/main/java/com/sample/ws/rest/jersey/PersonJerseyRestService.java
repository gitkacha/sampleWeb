package com.sample.ws.rest.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.entity.Person;
import com.sample.service.PersonService;

@Component
@Path("/rest")
public class PersonJerseyRestService {

	@Autowired
	PersonService service;
	
	@Path(value = "/api/person")
	@POST
	public Response  add( Person dto) {
System.out.println("Saving....");
		service.save(dto); 

		
		return Response.status(200).entity(dto).build();

	}

	/*@RequestMapping(value = "/api/person/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Person findById(@PathVariable("id") Long id) throws Exception {

		System.out.println("Finding....");
		Person found = service.get(id);

		return found;

	}
	*/
	@Path(value = "/api/persons")
	@GET
	public Response findAll() throws Exception {

		System.out.println("Finding all inside jersey...."+service);
		//List<Person> lst=service.getAll();
		//System.out.println("Returning all...."+lst);
		
		Integer count= new Integer(1);
		for(int i=0;i<100;i++)
		{
			count++;
		}
		System.out.println(count);
		return   Response.status(200).entity("Hi thr").build();

	}
	  

}
