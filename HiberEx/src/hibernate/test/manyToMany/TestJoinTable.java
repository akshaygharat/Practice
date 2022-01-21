package hibernate.test.manyToMany;
 

import hibernate.test.manyToMany.joinTable.*;
 
import java.util.HashSet;
import java.util.Set;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
 
public class TestJoinTable
{
    public static void main(String[] args) 
    {
    	StandardServiceRegistry registry =  new StandardServiceRegistryBuilder().configure().build();
        // Create MetadataSources
        MetadataSources sources = new MetadataSources(registry);
        // Create Metadata
        Metadata metadata = sources.getMetadataBuilder().build();
        // Create SessionFactory
        SessionFactory sessionFactory= metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        //Add subscription
        SubscriptionEntity subOne = new SubscriptionEntity();
        subOne.setSubscriptionName("Entertainment");
         
        SubscriptionEntity subTwo = new SubscriptionEntity();
        subTwo.setSubscriptionName("Horror");
         
        Set<SubscriptionEntity> subs = new HashSet<SubscriptionEntity>();
        subs.add(subOne);
        subs.add(subTwo);
         
        //Add readers
        ReaderEntity readerOne = new ReaderEntity();
        readerOne.setEmail("demo-user1@mail.com");
        readerOne.setFirstName("demo");
        readerOne.setLastName("user");
         
        ReaderEntity readerTwo = new ReaderEntity();
        readerTwo.setEmail("demo-user2@mail.com");
        readerTwo.setFirstName("demo");
        readerTwo.setLastName("user");
         
        Set<ReaderEntity> readers = new HashSet<ReaderEntity>();
        readers.add(readerOne);
        readers.add(readerTwo);
         
        readerOne.setSubscriptions(subs);
        readerTwo.setSubscriptions(subs);
 
        session.save(readerOne);
        session.save(readerTwo);
         
        session.getTransaction().commit();
       
    }
}