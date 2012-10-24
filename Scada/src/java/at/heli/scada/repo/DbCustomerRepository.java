/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.repo;

import at.heli.scada.entities.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author daniel
 */
@Stateless(name="dbCustomer")
public class DbCustomerRepository implements Repository<Customer> {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Customer entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Customer entity) {
        em.remove(entity);
    }

    @Override
    public Customer getById(int id) {
        return em.find(Customer.class, id);
    }

    @Override
    public List<Customer> getAll() {
        Query q = em.createNamedQuery("Customer.findAll");
        return q.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
