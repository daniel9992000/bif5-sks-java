/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.bl.interfaces.ExecutionResult;
import at.heli.scada.dal.MockCustomerRepository;
import at.heli.scada.dal.MockEngineerRepository;
import at.heli.scada.dal.interfaces.CustomerRepository;
import at.heli.scada.dal.interfaces.EngineerRepository;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Engineer;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daniel
 */
public class EngineerServiceTest {
    
    static EngineerRepository erepo;
    static CustomerRepository crepo;
    
    public EngineerServiceTest() {
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
        erepo = new MockEngineerRepository();
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCustomers method, of class EngineerService.
     */
    @Test
    public void getCustomersWithCustomer() throws Exception {
        //Arrange
        int engineerid = 1;
        EngineerService es = new EngineerService(erepo, crepo);
        Engineer e = erepo.getById(1);
        Customer c = new Customer(1);
        c.setEmail("danielherzog@gmx.at");
        c.setLastname("Herzog");
        c.setPassword("1234567");
        c.setFirstname("Daniel");
        c.setUsername("daniel");
        c.setEngineerid(e);
        crepo.save(c);
        
        //Act
        ExecutionResult<List<Customer>> result = es.getCustomers(engineerid);
        
        //Assert
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(1, result.getAffectedObject().size());
    }
    
    @Test
    public void getCustomersWithOutCustomer() throws Exception {
        
        //Arrange
        int engineerid = 1;
        EngineerService es = new EngineerService(erepo, crepo);
        
        //Act
        ExecutionResult<List<Customer>> result = es.getCustomers(engineerid);
        
        //Assert
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(0, result.getAffectedObject().size());
    }
}
