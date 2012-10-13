/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.beans;

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
public class EngineerSessionBean {

    @PersistenceContext
    EntityManager em;
    
    public List<Engineer> getEngineers()
    {
        Query q = em.createNamedQuery("Engineer.findAll");
        return (List<Engineer>)q.getResultList();
    }
    
    public void addEngineer(Integer id, String firstname, String lastname, String username, String password, String email)
    {
        Engineer e = new Engineer(id);
        e.setFirstname(firstname);
        e.setLastname(lastname);
        e.setUsername(username);
        e.setPassword(password);
        e.setEmail(email);
        em.persist(e);
    }
    
    public void deleteEngineer(Integer id)
    {
        Query q = em.createNamedQuery("Engineer.findByEngineerid");
        q.setParameter("engineerid", id);
        Engineer e = (Engineer)q.getSingleResult();
        em.remove(e);
    }

}
