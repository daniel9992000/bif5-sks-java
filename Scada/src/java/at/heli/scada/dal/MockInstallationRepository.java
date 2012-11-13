/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.dal.exception.DalException;
import at.heli.scada.dal.interfaces.InstallationRepository;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Installation;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniel
 */
public class MockInstallationRepository implements InstallationRepository {

    List<Installation> insts;
    
    public MockInstallationRepository()
    {
        insts = new ArrayList<Installation>();
    }
    
    @Override
    public void save(Installation entity) throws DalException {
        insts.add(entity);
    }

    @Override
    public void delete(Installation entity) throws DalException {
        insts.remove(entity);
    }

    @Override
    public Installation getById(int id) throws DalException {
        for(Installation i : insts)
        {
            if(i.getInstallationid() == id) {
                return i;
            }
        }
        return null;
    }

    @Override
    public List<Installation> getAll() throws DalException {
        return insts;
    }

    @Override
    public List<Installation> getByCustomerId(Customer c) throws DalException {
        List<Installation> tmp = new ArrayList<Installation>();
        for(Installation i : insts)
        {
            if(i.getCustomerid() == c)
            {
                tmp.add(i);
            }
        }
        
        return tmp;
    }
    
}
