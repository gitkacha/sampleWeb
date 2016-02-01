package com.sample.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sample.entity.Person;

//@Repository("personDao")
//@Transactional(isolation=Isolation.READ_UNCOMMITTED)
public class PersonDaoImpl implements PersonDao {
 
 //@PersistenceContext(type=PersistenceContextType.EXTENDED)
	@PersistenceContext
    private EntityManager em;
     
    @PersistenceUnit(name="jpaData")
    private EntityManagerFactory emf;
    
    
    /* (non-Javadoc)
	 * @see com.sample.dao.PersonDao#save(com.sample.entity.Person)
	 */
    @Override
	public Long save(Person person) {
    	//em.getTransaction().begin();
    	//System.out.println("while saving"+em.getTransaction());
    	if(null==person.getId())
        em.persist(person);
    	
    	else{
    		//em.getTransaction().begin();
    		//System.out.println("FIND");
//    		person = em.find(Person.class, person.getId());
//    		
//    		em.lock(person, LockModeType.PESSIMISTIC_WRITE);
    		person.setName(Thread.currentThread().getName());
    		System.out.println("MERGE...."+person.hashCode());
    		em.merge(person);
    		try {
    			System.out.println(Thread.currentThread().getName()+" have merged and going to sleep");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 System.out.println("After  update of "+Thread.currentThread().getName()+" " +get(person.getId()));
    		
    	}
    	//em.getTransaction().commit();
        return person.getId();
    }
    
    public EntityManager getEm()
    {
    	return emf.createEntityManager();
    }
     
    public Long saveWithEmf(Person person,EntityManager mgr) {
    	
    	
    	if(mgr==null)
    		mgr=emf.createEntityManager();
    	
    	mgr.getTransaction().begin();
    //	mgr.lock(person, LockModeType.);
    	if(null==person.getId())
    	{
    		
    		System.out.println("Persist");
    		
        mgr.persist(person);
     
    	}
    	else
    	{
    		System.out.println("Merge");
    		mgr.merge(person);
    	}
    	
    	  mgr.flush();
          mgr.getTransaction().commit();
    	
        return  person.getId();
    }
    public List<Person>getAll() {
        return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }
 
    /* (non-Javadoc)
	 * @see com.sample.dao.PersonDao#get(java.lang.Long)
	 */
    @Override
	public Person get(Long id) {
    	//System.out.println("While getting"+em);
    	return em.find(Person.class,id);
       // return em.createQuery("SELECT p FROM Person p where p.id = :id", Person.class).setParameter("id", id).getResultList().get(0);
    }
    public Person getWithEmf(Long id,EntityManager mgr) {
    	
        return mgr.createQuery("SELECT p FROM Person p where p.id = :id", Person.class).setParameter("id", id).getResultList().get(0);
    }
    
    public static void main(String[] args) {
    	 ApplicationContext context = new FileSystemXmlApplicationContext("C:\\Users\\emukkau\\Desktop\\Kaustuv\\ALGO-WKSP\\sampleWeb\\src\\main\\webapp\\WEB-INF\\mvc-dispatcher-servlet.xml");
                // ClassPathXmlApplicationContext("..\\WEB-INF\\mvc-dispatcher-servlet.xml");
    	 
    	   System.out.println(context.getBeansOfType(PersonDaoImpl.class).toString());
       final PersonDao dao = (PersonDao) context.getBean("personDao");
    
       Person km = new Person("Kaustuv", "Mukherjee");
     // Person ac = new Person("Abhishek", "Choudhury");
        
      final  Long id =dao.save(km);
      // dao.save(ac);
        
    /*  List<Person> persons = dao.getAll();
      for (Person person : persons) {
          System.out.println(person);
      }
      */
      
      final Person retrieved1=dao.get(id);
      final Person retrieved2=dao.get(id);
      
     /* retrieved1.setSurname("M");
	      dao.save(retrieved1);
	    System.out.println("After first update:"+dao.get(id));
	    
	    retrieved2.setName("KKK");
        dao.save(retrieved2);
        System.out.println("After second update:"+dao.get(id));*/
      
      new Thread( new Runnable(){

  		@Override
  		public void run() {
  			// TODO Auto-generated method stub
  			 retrieved1.setSurname("M");
  		      dao.save(retrieved1);
  		    System.out.println("After first update:"+dao.get(id));
  		}
      	
       }).start();
     
      new Thread( new Runnable(){
      @Override
		public void run() {
			// TODO Auto-generated method stub
    	  retrieved2.setName("KKK");
          dao.save(retrieved2);
          System.out.println("After second update:"+dao.get(id));
		}
    	
     }).start();
           
       
      
   
      
      
      
       
	}
    
    public static void main2(String[] args) {
   	 ApplicationContext context = new FileSystemXmlApplicationContext("C:\\Users\\emukkau\\Desktop\\Kaustuv\\ALGO-WKSP\\sampleWeb\\src\\main\\webapp\\WEB-INF\\mvc-dispatcher-servlet.xml");
               // ClassPathXmlApplicationContext("..\\WEB-INF\\mvc-dispatcher-servlet.xml");
   	 
   	   System.out.println(context.getBeansOfType(PersonDaoImpl.class).toString());
     final PersonDaoImpl dao = (PersonDaoImpl) context.getBean("personDao");
   
      Person km = new Person("Kaustuv", "Mukherjee");
    
       
     final  Long id =dao.saveWithEmf(km,null);
     
     // Long id= km.getId();
      System.out.println("ID:"+id);
    
     final EntityManager em1= dao.emf.createEntityManager();//dao.getEm();
    final EntityManager em2=  dao.emf.createEntityManager();//dao.getEm();
     
    final Person retrieved1=dao.getWithEmf(id,em1);
    final  Person retrieved2=dao.getWithEmf(id,em2);
     
     retrieved1.setSurname("M");
     new Thread( new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			 dao.saveWithEmf(retrieved1,em1); 
			  System.out.println("After first update:"+dao.getWithEmf(id,em1));
		}
    	
     }).start();
         
   
     
     retrieved1.setName("KKK");
     
     new Thread( new Runnable(){

		@Override
		public void run() {
try {
	Thread.sleep(5000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}		
dao.saveWithEmf(retrieved1,em2); 
			 System.out.println("After second update:"+dao.getWithEmf(id,em2));
		}
    	
     }).start();
     
    // dao.saveWithEmf(retrieved1,em2);
    
     
     
     
      
	}
}