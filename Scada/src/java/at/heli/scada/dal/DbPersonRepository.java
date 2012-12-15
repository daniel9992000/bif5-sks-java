/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.dal.interfaces.DalException;
import at.heli.scada.dal.interfaces.PersonRepository;
import at.heli.scada.entities.Person;
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
public class DbPersonRepository implements PersonRepository {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public Person getByUsername(String username) throws DalException {
        Person tmp = null;
        
        try
        {
            Query q = em.createNamedQuery("Person.findByUsername");
            q.setParameter("username", username);
            tmp = (Person)q.getSingleResult();
        }
        catch(Exception err)
        {
            throw new DalException("cannot fetch all customer", err);
        }
        return tmp;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
