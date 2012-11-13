/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.dal.interfaces.InstallationRepository;
import at.heli.scada.dal.qualifier.DbInstallationQualifier;
import at.heli.scada.dal.exception.DalException;
import at.heli.scada.entities.Customer;
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
@DbInstallationQualifier
public class DbInstallationRepository implements InstallationRepository {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Installation entity) throws DalException {
        try
        {
            em.persist(entity);
        }
        catch(Exception err)
        {
            throw new DalException("cannot save installation", err);
        }
    }

    @Override
    public void delete(Installation entity) throws DalException {
        try
        {
            em.remove(entity);
        }
        catch(Exception err)
        {
            throw new DalException("cannot delete installation", err);
        }
    }

    @Override
    public Installation getById(int id) throws DalException {
        Installation i;
        try
        {
            i = em.find(Installation.class, id);
        }
        catch(Exception err)
        {
            throw new DalException("cannot find installation with id "+ id,  err);
        }
        return i;
    }

    @Override
    public List<Installation> getAll() throws DalException {
        List<Installation> tmp;
        try
        {
            Query q = em.createNamedQuery("Installation.findAll");
            tmp = q.getResultList();
        }
        catch(Exception err)
        {
            throw new DalException("cannot fetch all installations", err);
        }
        
        return tmp;
    }

    @Override
    public List<Installation> getByCustomerId(Customer c) throws DalException {
        List<Installation> tmp;
        try
        {
            Query q = em.createNamedQuery("Installation.findByCustomerId");
            q.setParameter("customerid", c);
            tmp = q.getResultList();
        }
        catch(Exception err)
        {
            throw new DalException("cannot fetch all installations", err);
        }
        
        return tmp;
    }

}
