package spring.hibernate.persistence.dao.impl;

import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import spring.hibernate.models.Customer;
import spring.hibernate.persistence.dao.CustomerDao;

public class CustomerDaoImpl implements CustomerDao {
    
    private HibernateTemplate hibernateTemplate;
    private TransactionTemplate transactionTemplate;


    public TransactionTemplate getTransactionTemplate() {
        return this.transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }


    public HibernateTemplate getHibernateTemplate() {
        return this.hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public Customer getCustomerById(int id){
       Customer c = hibernateTemplate.get(Customer.class, id);
       c.display();
       return c;
    }

    @Override
    public List<Customer> getAllCustomers(){

        return hibernateTemplate.loadAll(Customer.class);
    }

    @Override
    public void addCustomer(Customer c){

        transactionTemplate.execute(new TransactionCallback<Object>() {

            @Override
            @Nullable
            public Object doInTransaction(TransactionStatus arg0) {

                hibernateTemplate.saveOrUpdate(c);
                return arg0;
            }
            

        });
    }

    @Override
    public void updateCustomer(int id ,Customer c){

        transactionTemplate.execute(new TransactionCallback<Object>() {

            @Override
            @Nullable
            public Object doInTransaction(TransactionStatus arg0) {

                hibernateTemplate.update(c);
                return arg0;
            }
            

        });
    }

    @Override
    public void deleteCustomer(Customer c){
        transactionTemplate.execute(new TransactionCallback<Object>() {

            @Override
            @Nullable
            public Object doInTransaction(TransactionStatus arg0) {

                hibernateTemplate.delete(c);
                return arg0;
            }
            

        });
    }



}
