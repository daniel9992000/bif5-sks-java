/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.dal.interfaces.DalException;
import at.heli.scada.dal.interfaces.CustomerRepository;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Engineer;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author daniel
 */
@Stateless
@Alternative
public class DbCustomerRepository implements CustomerRepository {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Customer entity) throws DalException {
        try  
        {
            em.persist(entity);
        }
        catch(Exception err)
        {
            throw new DalException("cannot save customer", err);
        }
    }

    @Override
    public void delete(Customer entity) throws DalException {
        try
        {
            em.remove(entity);
        }
        catch(Exception err)
        {
            throw new DalException("cannot delete customer", err);
        }
    }

    @Override
    public Customer getById(int id) throws DalException {
        Customer c = null;
        try
        {
           c = em.find(Customer.class, id); 
        }
        catch(Exception err)
        {
            throw new DalException("cannot find customer with id " + id, err);
        }
        return c;
    }

    @Override
    public List<Customer> getAll() throws DalException {
        List<Customer> tmp = null;
        
        try
        {
            Query q = em.createNamedQuery("Customer.findAll");
            tmp = q.getResultList();
        }
        catch(Exception err)
        {
            throw new DalException("cannot fetch all customer", err);
        }
        return tmp;
    }

    @Override
    public List<Customer> getByEngineerId(Engineer entity) throws DalException {
        List<Customer> tmp = null;
        try
        {
            Query q = em.createNamedQuery("Customer.findByEngineerId");
            q.setParameter("engineerid", entity);
            tmp = q.getResultList();
        }
        catch(Exception err)
        {
            throw new DalException("cannot fetch all customers", err);
        }
        
        return tmp;
    }
}
