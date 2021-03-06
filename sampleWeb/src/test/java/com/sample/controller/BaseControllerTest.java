package com.sample.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sample.service.PersonServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("development")
@WebAppConfiguration("classpath*:web.xml")
@ContextConfiguration("classpath*:WEB-INF/mvc-dispatcher-servlet.xml")

public class BaseControllerTest {

    @Autowired WebApplicationContext wac; 
    @Autowired MockHttpSession session;
    @Autowired MockHttpServletRequest request;

    private MockMvc mockMvc;

    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

	@Test
	public void testWelcomeName() throws Exception
	{
		 mockMvc.perform(get("/Kaustuv"))
        .andExpect(status().isOk())
         .andExpect(view().name("index"))
         .andExpect(forwardedUrl("/WEB-INF/pages/index.jsp"))
         .andExpect(model().attribute("message", "Welcome Kaustuv"));
		 System.out.println("Here");
	}
}
