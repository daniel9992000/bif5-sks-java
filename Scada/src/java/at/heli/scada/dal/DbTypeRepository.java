/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.dal.interfaces.DalException;
import at.heli.scada.dal.interfaces.TypeRepository;
import at.heli.scada.entities.MeasurementType;
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
public class DbTypeRepository implements TypeRepository {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(MeasurementType entity) throws DalException {
        em.persist(entity);
    }

    @Override
    public void delete(MeasurementType entity) throws DalException {
        em.remove(entity);
    }

    @Override
    public MeasurementType getById(int id) throws DalException {
        return em.find(MeasurementType.class, id);
    }

    @Override
    public List<MeasurementType> getAll() throws DalException {
        Query q = em.createNamedQuery("MeasurementType.findAll");
        return q.getResultList();
    }
}
