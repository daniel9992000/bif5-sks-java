/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

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
@Stateless(name="dbEngineer")
public class DbEngineerRepository implements Repository<Engineer> {
    
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
        List<Engineer> tmp;
        
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
    public Engineer getById(int id) throws DalException {
        Engineer e;
        try
        {
            e = em.find(Engineer.class, id);
        }
        catch(Exception err)
        {
            throw new DalException("cannot find engineer with id " + id, err);
        }
        return e;
    }

}
