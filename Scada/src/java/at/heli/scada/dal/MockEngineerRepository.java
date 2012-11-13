/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.dal.exception.DalException;
import at.heli.scada.dal.interfaces.EngineerRepository;
import at.heli.scada.entities.Engineer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniel
 */
public class MockEngineerRepository implements EngineerRepository {
    
    List<Engineer> engineers;
    
    public MockEngineerRepository()
    {
        engineers = new ArrayList<Engineer>();
        
        Engineer e = new Engineer(1);
        e.setEmail("danielherzog@outlook.com");
        e.setFirstname("Daniel");
        e.setLastname("Herzog");
        e.setPassword("1234567");
        e.setUsername("danielherzog");
        engineers.add(e);
    }

    @Override
    public void save(Engineer entity) throws DalException {
        engineers.add(entity);
    }

    @Override
    public void delete(Engineer entity) throws DalException {
        engineers.remove(entity);
    }

    @Override
    public Engineer getById(int id) throws DalException {
        for(Engineer e : engineers)
        {
            if(e.getPersonid() == id)
            {
                return e;
            }
        }
        return null;    
    }

    @Override
    public List<Engineer> getAll() throws DalException {
        return engineers;
    }
    
}
