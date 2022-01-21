package prudent.study;


import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.Service;

import study.entity.Address;
import study.entity.Employee;
import study.entity.StudentEntity;

public class HibernateApplication {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory =null;
		
		
        StandardServiceRegistry registry =  new StandardServiceRegistryBuilder().configure().build();
             // Create MetadataSources
             MetadataSources sources = new MetadataSources(registry);
             // Create Metadata
             Metadata metadata = sources.getMetadataBuilder().build();
             // Create SessionFactory
             sessionFactory= metadata.getSessionFactoryBuilder().build();
             Session session = sessionFactory.openSession(); // start a transaction
             //update(session);
             //delete(session);
//             showAll(session);
		/*
		 * Transaction transaction = session.beginTransaction();
		 * 
		 * StudentEntity obj = new StudentEntity(); obj.setRollNumber(6);
		 * obj.setStudentName("prakash"); session.save(obj);
		 * 
		 * // commit transaction transaction.commit();
		 */  
 
             Transaction transaction = session.beginTransaction();
             Employee e1=new Employee();    
             e1.setName("Ravi Malik");    
             e1.setEmail("ravi@gmail.com");    
                 
             Address address1=new Address();    
             address1.setAddressLine1("G-21,Lohia nagar");    
             address1.setCity("Ghaziabad");    
             address1.setState("UP");    
             address1.setCountry("India");    
             address1.setPincode(201301);    
                 
             e1.setAddress(address1);    
             address1.setEmployee(e1);    
                 
             session.save(e1);    
             
             transaction.commit();
             
             
             
	}//end of main
	
	
	public static void update(Session session)
	{
		Transaction transaction = session.beginTransaction();
        // save the student objects
        
		
        StudentEntity obj = session.find(StudentEntity.class, 2);
   
        obj.setStudentName("sharayu");
        session.save(obj);   
        
        // commit transaction
        transaction.commit();
		
	}
	
	public static void delete(Session session)
	{
		Transaction transaction = session.beginTransaction();
        // save the student objects
        
        StudentEntity obj = session.find(StudentEntity.class, 2);
   
        session.delete(obj);
        
        // commit transaction
        transaction.commit();

		
	}
	
	
	public static void showAll(Session session)
	{
		/*
		 * Query query = session.
		 * createQuery("from study.entity.StudentEntity s where s.studentName like 'pr%'"
		 * ); query.getResultList().stream().forEach((s)->{System.out.println(s);});
		 */
		
		/*
		 * Query query =
		 * session.createNativeQuery("select * from my_student",StudentEntity.class);
		 * query.getResultStream().forEach((x)->{System.out.println(x);});
		 */
		
        CriteriaQuery<StudentEntity> cq = session.getCriteriaBuilder().createQuery(StudentEntity.class);
        
         session.createQuery(cq).getResultList().stream().forEach((a)->{System.out.println(a);});
		
	}
	
	
}//end of class

		
		
	
	
	
		
		
