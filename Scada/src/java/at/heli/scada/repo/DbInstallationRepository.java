/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.repo;

import at.heli.scada.entities.Installation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author daniel
 */
@Stateless
public class DbInstallationRepository implements Repository<Installation> {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Installation entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Installation entity) {
        em.remove(entity);
    }

    @Override
    public Installation getById(int id) {
        return em.find(Installation.class, id);
    }

    @Override
    public List<Installation> getAll() {
        Query q = em.createNamedQuery("Installation.findAll");
        return q.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
