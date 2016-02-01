package com.sample.dao;

import java.util.List;

import com.sample.entity.Person;

public interface PersonDao {

	Long save(Person person);

	Person get(Long id);

	List<Person> getAll();

}