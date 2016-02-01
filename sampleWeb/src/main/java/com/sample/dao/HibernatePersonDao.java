package com.sample.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.entity.Person;

@Repository("hibernatePersonDao")
@Transactional
public class HibernatePersonDao implements PersonDao{

	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Long save(Person person) {
		
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(person);
		return person.getId();
	}

	@Override
	public Person get(Long id) {
		// TODO Auto-generated method stub
		return (Person) sessionFactory.getCurrentSession().get(Person.class, id);
	}

	public static void main(String[] args) {
		
		ApplicationContext ctx= new ClassPathXmlApplicationContext("classpath*:WEB-INF\\mvc-dispatcher-servlet.xml");
		 System.out.println(ctx.getBeanDefinitionNames().length);
		PersonDao dao= (PersonDao) ctx.getBean("hibernatePersonDao");
		 Person km = new Person("Kaustuv", "Mukherjee");
		
		 
		System.out.println(dao.get(dao.save(km)));
		 

	}

	@Override
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
