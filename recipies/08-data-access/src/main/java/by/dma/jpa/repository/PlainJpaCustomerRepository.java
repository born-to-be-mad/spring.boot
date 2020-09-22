package by.dma.jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.dma.jpa.entity.Customer;

@Repository
public class PlainJpaCustomerRepository implements ICustomerRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Customer> findAll() {
    return em.createQuery("SELECT c FROM Customer c", Customer.class)
            .getResultList();
  }

  @Override
  public Customer findById(long id) {
    return em.find(Customer.class, id);
  }

  @Override
  public Customer save(Customer customer) {
    em.persist(customer);
    return customer;
  }

  @Override
  public void delete(Customer customer) {
    em.remove(customer);
  }
}
