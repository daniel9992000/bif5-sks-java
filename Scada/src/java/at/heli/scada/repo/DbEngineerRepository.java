/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.repo;

import at.heli.scada.entities.Engineer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author daniel
 */
@Stateless(name="dbEngineer")
public class DbEngineerRepository implements Repository<Engineer> {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Engineer engineer) {
        em.persist(engineer);
    }

    @Override
    public void delete(Engineer engineer) {
        em.remove(engineer);
    }

    @Override
    public List<Engineer> getAll() {
        Query q = em.createNamedQuery("Engineer.findAll");
        return q.getResultList();
    }

    @Override
    public Engineer getById(int id) {
        return em.find(Engineer.class, id);
    }

}
