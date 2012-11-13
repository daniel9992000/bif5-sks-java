/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.dal.interfaces.Repository;
import at.heli.scada.dal.qualifier.MockCustomerQualifier;
import at.heli.scada.dal.exception.DalException;
import at.heli.scada.dal.interfaces.CustomerRepository;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Engineer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniel
 */
@MockCustomerQualifier
public class MockCustomerRepository implements CustomerRepository {
    
    List<Customer> customers;
    
    public MockCustomerRepository()
    {
        customers = new ArrayList<Customer>();
        Customer tmp = new Customer(1);
        tmp.setEmail("danielherzog@gmx.at");
        tmp.setFirstname("Daniel");
        tmp.setLastname("Herzog");
        tmp.setPassword("1234567");
        tmp.setUsername("daniel");
        customers.add(tmp);
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

    @Override
    public List<Customer> getByEngineerId(Engineer entity) throws DalException {
        List<Customer> tmp = new ArrayList<Customer>();
        
        for(Customer c : customers)
        {
            if(c.getEngineerid() == entity) {
                tmp.add(c);
            }
        }
        return tmp;
    }
    
}
