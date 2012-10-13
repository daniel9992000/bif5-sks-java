/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.beans;

import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Engineer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Daniel
 */
@Stateless
public class CustomerSessionBean {

    @PersistenceContext
    EntityManager em;
    
    public List<Customer> getCustomer()
    {
        Query q = em.createNamedQuery("Customer.findAll");
        return (List<Customer>)q.getResultList();
    }
    
    public void addCustomer(Integer id, String firstname, String lastname, String username, String password, String email, Engineer engineer)
    {
        Customer c = new Customer(id);
        c.setFirstname(firstname);
        c.setLastname(lastname);
        c.setUsername(username);
        c.setPassword(password);
        c.setEmail(email);
        c.setEngineerid(engineer);
        em.persist(c);
    }
    
    public void deleteCustomer(Integer id)
    {
        Query q = em.createNamedQuery("Customer.findByCustomerid");
        q.setParameter("customerid", id);
        Customer c = (Customer)q.getSingleResult();
        em.remove(c);
    }
}
