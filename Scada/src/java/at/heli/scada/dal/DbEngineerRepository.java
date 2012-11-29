/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.dal.qualifier.DbEngineerQualifier;
import at.heli.scada.dal.interfaces.DalException;
import at.heli.scada.dal.interfaces.EngineerRepository;
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
@Stateless
@DbEngineerQualifier
public class DbEngineerRepository implements EngineerRepository {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Engineer entity) throws DalException {
        try
        {
            em.persist(entity);
        }
        catch(Exception err)
        {
            throw new DalException("cannot save engineer", err);
        }
    }

    @Override
    public void delete(Engineer entity) throws DalException { 
        try
        {
            em.remove(entity);
        }
        catch(Exception err)
        {
            throw new DalException("cannot delete engineer", err);
        }
    }

    @Override
    public List<Engineer> getAll() throws DalException {
        List<Engineer> tmp = null;
        
        try
        {
            Query q = em.createNamedQuery("Engineer.findAll");
            tmp = q.getResultList();
        }
        catch(Exception err)
        {
            throw new DalException("cannot fetch all engineers", err);
        }
        return tmp;
    }

    @Override
    public Engineer getById(int personid) throws DalException {
        Engineer e = null;
        try
        {
            e = em.find(Engineer.class, personid);
        }
        catch(Exception err)
        {
            throw new DalException("cannot find engineer with id " + personid, err);
        }
        return e;
    }
}
