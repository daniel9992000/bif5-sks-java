/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal.interfaces;

import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Engineer;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface EngineerRepository {
    public void save(Engineer entity) throws DalException;
    public void delete(Engineer entity) throws DalException;
    public Engineer getById(int id) throws DalException;
    public List<Engineer> getAll() throws DalException;
}
