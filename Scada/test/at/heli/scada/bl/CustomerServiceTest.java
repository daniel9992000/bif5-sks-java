/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.bl.exception.BLException;
import at.heli.scada.dal.InstallationRepository;
import at.heli.scada.dal.MockCustomerRepository;
import at.heli.scada.dal.Repository;
import at.heli.scada.dal.exception.DalException;
import at.heli.scada.entities.Customer;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daniel
 */
public class CustomerServiceTest {
    
    static Repository<Customer> crepo;
    
    public CustomerServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        crepo = new MockCustomerRepository();
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void createCustomerWithNoErrors() throws BLException, DalException
    {
        Customer c = new Customer();
        c.setEmail("danielherzog@gmx.at");
        c.setLastname("Herzog");
        c.setPassword("1234567");
        c.setFirstname("Daniel");
        c.setUsername("daniel");
        c.setPersonid(1);
        CustomerService cs = new CustomerService(null, crepo);
        cs.createCustomer(c);
        Assert.assertEquals(1, crepo.getAll().size());
    }
    
    @Test
    public void createCustomerWithError() throws DalException, BLException
    {
        Customer c = new Customer();
        c.setEmail("danielherzog@gmx.at");
        c.setLastname("Herzog");
        c.setPassword("1234567");
        c.setFirstname("Daniel");
        c.setUsername("");
        c.setPersonid(1);
        CustomerService cs = new CustomerService(null, crepo);
        cs.createCustomer(c);
        Assert.assertEquals(0, crepo.getAll().size());
    }
}
