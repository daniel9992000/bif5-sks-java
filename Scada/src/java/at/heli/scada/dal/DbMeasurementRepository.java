/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.dal.exception.DalException;
import at.heli.scada.entities.Installation;
import at.heli.scada.entities.Measurement;
import java.util.Calendar;
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
public class DbMeasurementRepository implements MeasurementRepository {
    
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

    @Override
    public List<Measurement> getPerDay(Installation i, Calendar time) throws DalException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Measurement> getPerMonth(Installation i, Calendar time) throws DalException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Measurement> getPerYear(Installation i, Calendar time) throws DalException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
