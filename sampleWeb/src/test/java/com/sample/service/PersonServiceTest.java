package com.sample.service;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sample.dao.PersonDaoImpl;
import com.sample.entity.Person;


public class PersonServiceTest {

	@Mock
	PersonDaoImpl dao;
	
	@InjectMocks
	PersonService ps= new PersonServiceImpl();
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}
	@Test
	 public void testGet() {
		
		Mockito.when(dao.getAll()).thenReturn(Arrays.asList(new Person[]{ new Person("bc", "bc")})).thenReturn(Arrays.asList(new Person[]{ new Person("Kaustuv", "Mukherjee"),new Person("Orion", "Hunter")}));
		
		List<Person> persons=ps.getAll();
		for (Person person : persons) {
	           System.out.println(person);
	       }
	 }
}
