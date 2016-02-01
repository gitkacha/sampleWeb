package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sample.dao.PersonDao;
import com.sample.entity.Person;

@Component
public class PersonServiceImpl implements PersonService {

	@Autowired
	@Qualifier("hibernatePersonDao")
	PersonDao dao;
	
	/* (non-Javadoc)
	 * @see com.sample.service.PersonService#save(com.sample.entity.Person)
	 */
	@Override
	public Long save(Person person)
	{
		return dao.save(person);
	}
	
	 /* (non-Javadoc)
	 * @see com.sample.service.PersonService#getAll()
	 */
	@Override
	public List<Person>getAll() {
		 return dao.getAll();
	 }
	/* (non-Javadoc)
	 * @see com.sample.service.PersonService#get(java.lang.Long)
	 */
	@Override
	public Person get(Long id) {
		 return dao.get(id);
	 }
}
