package com.sample.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sample.entity.Person;
@Component

public interface PersonService {

	Long save(Person person);

	List<Person> getAll();

	Person get(Long id);

}