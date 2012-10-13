/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.beans;

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
public class MeasurementTypeSessionBean {

    @PersistenceContext
    EntityManager em;
    
    public List<MeasurementType> getInstallations()
    {
        Query q = em.createNamedQuery("MeasurementType.findAll");
        return (List<MeasurementType>)q.getResultList();
    }
    
    public void addMeasurementType(Integer id)
    {
        MeasurementType t = new MeasurementType(id);       
        em.persist(t);
    }
    
    public void deleteMeasurementType(Integer id)
    {
        Query q = em.createNamedQuery("MeasurementType.findByTypeid");
        q.setParameter("typeid", id);
        MeasurementType t = (MeasurementType)q.getSingleResult();
        em.remove(t);
    }

}
