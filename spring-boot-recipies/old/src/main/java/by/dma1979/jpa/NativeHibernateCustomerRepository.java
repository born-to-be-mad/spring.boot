package by.dma1979.jpa;

import by.dma1979.jpa.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:47
 * @since : 2019.12
 **/
@Repository
@Transactional
public class NativeHibernateCustomerRepository implements ICustomerRepository {

    private final SessionFactory sessionFactory;

    NativeHibernateCustomerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Customer> findAll() {
        String hql = "SELECT c FROM Customer c";
        var query = getSession().createQuery(hql, Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(long id) {
        return getSession().find(Customer.class, id);
    }

    @Override
    public Customer save(Customer customer) {
        getSession().persist(customer);
        return customer;
    }

    @Override
    public void delete(Customer customer) {
        getSession().delete(customer);
    }

}

