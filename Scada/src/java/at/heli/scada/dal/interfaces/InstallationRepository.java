/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal.interfaces;

import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Installation;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface InstallationRepository {
    
    public void save(Installation entity) throws DalException;
    public void delete(Installation entity) throws DalException;
    public Installation getById(int id) throws DalException;
    public List<Installation> getAll() throws DalException;
    public List<Installation> getByCustomerId(Customer c) throws DalException;
    public Installation getBySerialNo(String serialno) throws DalException;
    
}
