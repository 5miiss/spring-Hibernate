package spring.hibernate;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.hibernate.config.ConfigClass;
import spring.hibernate.models.Customer;
import spring.hibernate.persistence.dao.CustomerDao;
import spring.hibernate.persistence.dao.impl.CustomerDaoSession;


public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ConfigClass.class);
        context.refresh();
        // ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        CustomerDao customerDao =(CustomerDao) context.getBean("gCustomerDao");

        // get custmer by id

        // customerDao.getCustomerById(5);

        //get all customers

        // List<Customer> customers = customerDao.getAllCustomers();
        // customers.forEach(c->c.display());

        //to save a new customer

        // Customer c =context.getBean("customer",Customer.class);
        // customerDao.addCustomer(c);
        

        //===========================================================================

        CustomerDaoSession customerDaoSession = context.getBean("gCustomerDaoSession",CustomerDaoSession.class);

        Customer cust =customerDaoSession.getCustomerById(7) ;
        // customerDao.deleteCustomer(cust);

        cust.setLastOrdered(Timestamp.valueOf("2022-11-03 11:05:32"));

        customerDaoSession.addCustomer(context.getBean("customer",Customer.class));
        // customerDaoSession.addCustomer(context.getBean("customer", Customer.class));
        customerDaoSession.getAllCustomers().forEach(c->c.display());



        context.close();
    }
}
