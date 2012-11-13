/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.dal.interfaces.Repository;
import at.heli.scada.dal.qualifier.DbTypeQualifier;
import at.heli.scada.entities.MeasurementType;
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
@DbTypeQualifier
public class DbTypeRepository implements Repository<MeasurementType>{
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(MeasurementType entity) {
        em.persist(entity);
    }

    @Override
    public void delete(MeasurementType entity) {
        em.remove(entity);
    }

    @Override
    public MeasurementType getById(int id) {
        return em.find(MeasurementType.class, id);
    }

    @Override
    public List<MeasurementType> getAll() {
        Query q = em.createNamedQuery("MeasurementType.findAll");
        return q.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
