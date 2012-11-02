/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.entities.Measurement;
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
public class DbMeasurementRepository implements Repository<Measurement> {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Measurement entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Measurement entity) {
        em.remove(entity);
    }

    @Override
    public Measurement getById(int id) {
        return em.find(Measurement.class, id);
    }

    @Override
    public List<Measurement> getAll() {
        Query q = em.createNamedQuery("Measurement.findAll");
        return q.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
