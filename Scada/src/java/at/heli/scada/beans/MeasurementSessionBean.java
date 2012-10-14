/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.beans;

import at.heli.scada.entities.Measurement;
import at.heli.scada.entities.MeasurementType;
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
public class MeasurementSessionBean {

    @PersistenceContext
    EntityManager em;
    
    public List<Measurement> getMeasurements()
    {
        Query q = em.createNamedQuery("Measurement.findAll");
        return (List<Measurement>)q.getResultList();
    }
    
    public void addMeasurement(Integer id)
    {
        Measurement m = new Measurement(id);       
        em.persist(m);
    }
    
    public void deleteMeasurement(Integer id)
    {
        Query q = em.createNamedQuery("Measurement.findByMeasid");
        q.setParameter("measid", id);
        Measurement m = (Measurement)q.getSingleResult();
        em.remove(m);
    }

}
