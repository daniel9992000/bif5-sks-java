/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.beans;

import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Installation;
import java.math.BigDecimal;
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
public class InstallationSessionBean {

    @PersistenceContext
    EntityManager em;
    
    public List<Installation> getInstallations()
    {
        Query q = em.createNamedQuery("Installation.findAll");
        return (List<Installation>)q.getResultList();
    }
    
    public void addInstallation(Integer id, String serialno, BigDecimal longi, BigDecimal lati, String description, Customer customerid)
    {
        Installation i = new Installation(id);
        i.setSerialno(serialno);
        i.setLongitude(longi);
        i.setLatitude(lati);
        i.setDescription(description);
        i.setCustomerid(customerid);
        em.persist(i);
    }
    
    public void deleteInstallation(Integer id)
    {
        Query q = em.createNamedQuery("Installation.findByInstallationid");
        q.setParameter("installationid", id);
        Installation i = (Installation)q.getSingleResult();
        em.remove(i);
    }

}
