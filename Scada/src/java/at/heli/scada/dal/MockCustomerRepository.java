/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.dal.qualifier.MockCustomerQualifier;
import at.heli.scada.dal.exception.DalException;
import at.heli.scada.entities.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniel
 */
@MockCustomerQualifier
public class MockCustomerRepository implements Repository<Customer> {
    
    List<Customer> customers;
    
    public MockCustomerRepository()
    {
        customers = new ArrayList<Customer>();
    }

    @Override
    public void save(Customer entity) throws DalException {
        customers.add(entity);
    }

    @Override
    public void delete(Customer entity) throws DalException {
        customers.remove(entity);
    }

    @Override
    public Customer getById(int id) throws DalException {
        for(Customer c : customers)
        {
            if(c.getPersonid() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Customer> getAll() throws DalException {
        return customers;
    }
    
}
