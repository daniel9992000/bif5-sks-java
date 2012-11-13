/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal.interfaces;

import at.heli.scada.dal.exception.DalException;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Engineer;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface CustomerRepository {
    public void save(Customer entity) throws DalException;
    public void delete(Customer entity) throws DalException;
    public Customer getById(int id) throws DalException;
    public List<Customer> getAll() throws DalException;
    public List<Customer> getByEngineerId(Engineer entity) throws DalException;
    
}
