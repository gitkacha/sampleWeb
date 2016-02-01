package com.sample.ws.rest.spring;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.entity.Person;
import com.sample.service.PersonService;

/*@Configuration
class TestContext{
	 @Bean
	    public PersonService personService() {
	        return Mockito.mock(PersonService.class);
	    }
}*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:WEB-INF/mvc-dispatcher-servlet.xml","classpath*:TestContext.xml"} )
@WebAppConfiguration("classpath*:web.xml")
public class PersonRestServiceControllerTest {

	@Autowired
	WebApplicationContext wac;
	
	@Autowired
	//@Spy
	// @InjectMocks
	//@Mock
	PersonService service;

	/*
	 * @Mock PersonDao dao;
	 */

	MockMvc mockMvc;

	@Before
	public void init() {
		//Mockito.reset(service);
		
		mockMvc=MockMvcBuilders.webAppContextSetup(this.wac).build();
		MockitoAnnotations.initMocks(this);
		

	}

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);

	}

	/*@Test
	public void add() {

		Person km = new Person("Kaustuv", "Mukherjee");
		service.save(km);

	}

	@Test
	public void getOne() {

		Person obj = service.get(1L);

	}*/

	@Test
	public void getAll() throws Exception {
System.out.println("PErsonService:"+service);
		Mockito.when(service.getAll()).thenReturn(Arrays.asList( new Person("Citius", "Citius"),new Person("Altius", "Altius"), new Person("Fortius", "Fortius") ));
		mockMvc.perform(get("/api/persons").accept(APPLICATION_JSON_UTF8))

				.andExpect(status().isOk())

				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].name", is("Citius")))
				.andDo(print());
				/*.andExpect(jsonPath("$[0].title", is("Foo"))).andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].description", is("Lorem ipsum")))
				.andExpect(jsonPath("$[1].title", is("Bar")));*/

		verify(service, times(1)).getAll();

		verifyNoMoreInteractions(service);
		
		 //ArgumentCaptor<Person> dtoCaptor = ArgumentCaptor.forClass(Person.class);


	}
}
