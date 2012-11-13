/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.bl.exception.BLException;
import at.heli.scada.dal.interfaces.InstallationRepository;
import at.heli.scada.dal.MockCustomerRepository;
import at.heli.scada.dal.MockInstallationRepository;
import at.heli.scada.dal.interfaces.Repository;
import at.heli.scada.dal.exception.DalException;
import at.heli.scada.dal.interfaces.CustomerRepository;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Installation;
import java.math.BigInteger;
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
    
    static CustomerRepository crepo;
    static InstallationRepository irepo;
    
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
        irepo = new MockInstallationRepository();
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void createCustomerWithNoErrors() throws BLException, DalException
    {
        //Arrange
        Customer c = new Customer();
        c.setEmail("danielherzog@gmx.at");
        c.setLastname("Herzog");
        c.setPassword("1234567");
        c.setFirstname("Daniel");
        c.setUsername("daniel");
        c.setPersonid(2);
         
        //Act
        CustomerService cs = new CustomerService(null, crepo);
        ExecutionResult<Customer> result = cs.createCustomer(c);
        
        
        //Assert
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(result.getErrors(), null);
        Assert.assertEquals(c, result.getAffectedObject());
    }
    
    @Test
    public void createCustomerWithErrorOnUsername() throws DalException, BLException
    {
        //Arrange
        Customer c = new Customer();
        c.setEmail("danielherzog@gmx.at");
        c.setLastname("Herzog");
        c.setPassword("1234567");
        c.setFirstname("Daniel");
        c.setUsername("");
        c.setPersonid(2);
        
        //Act
        CustomerService cs = new CustomerService(null, crepo);
        ExecutionResult<Customer> result = cs.createCustomer(c);
        
        
        //Assert
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals(result.getErrors().size(), 1);
        Assert.assertEquals(c, result.getAffectedObject());
    }
    
    @Test
    public void createCustomerPasswordTooShort() throws DalException, BLException
    {
        //Arrange
        Customer c = new Customer();
        c.setEmail("danielherzog@gmx.at");
        c.setLastname("Herzog");
        c.setPassword("12345");
        c.setFirstname("Daniel");
        c.setUsername("daniel");
        c.setPersonid(2);
        
        //Act
        CustomerService cs = new CustomerService(null, crepo);
        ExecutionResult<Customer> result = cs.createCustomer(c);
        
        //Assert
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals(result.getErrors().size(), 1);
        Assert.assertEquals(c, result.getAffectedObject());
    }
    
    @Test
    public void createCustomerEmpty() throws DalException, BLException
    {
        //Arrange
        Customer c = new Customer();
        
        //Act
        CustomerService cs = new CustomerService(null, crepo);
        ExecutionResult<Customer> result = cs.createCustomer(c);
        
        //Assert
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals(5, result.getErrors().size());
        Assert.assertEquals(c, result.getAffectedObject());
    }
    
    @Test
    public void getCustomerNoError() throws DalException, BLException
    {
        //Arrange
        int cid = 1;
        
        Customer c = crepo.getById(cid);
        
        //Act
        CustomerService cs = new CustomerService(null, crepo);
        ExecutionResult<Customer> result = cs.getCustomer(cid);
        
        //Assert
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(c, result.getAffectedObject());
    }
    
    @Test
    public void getCustomerWithError() throws DalException, BLException
    {
        //Arrange
        int cid = 100;
        
        
        //Act
        CustomerService cs = new CustomerService(null, crepo);
        ExecutionResult<Customer> result = cs.getCustomer(cid);
        
        //Assert
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(null, result.getAffectedObject());
    }
    
    @Test
    public void getInstallationCustomerHasInstallation() throws DalException, BLException
    {
        //Arrange
        Customer c = crepo.getById(1);
        
        Installation i = new Installation();
        i.setCustomerid(c);
        i.setDescription("Heizung");
        i.setInstallationid(1);
        i.setLatitude(BigInteger.ZERO);
        i.setLongitude(BigInteger.ZERO);
        i.setSerialno("1234567890");
        irepo.save(i);
        
        //Act
        CustomerService cs = new CustomerService(irepo, crepo);
        ExecutionResult<List<Installation>> result = cs.getInstallations(c);
        
        //Assert
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(1, result.getAffectedObject().size());
    }
    
    @Test
    public void getInstallationCustomerHasNoInstallations() throws DalException, BLException
    {
        //Arrange
        Customer c = crepo.getById(1);
        
        //Act
        CustomerService cs = new CustomerService(irepo, crepo);
        ExecutionResult<List<Installation>> result = cs.getInstallations(c);
        
        //Assert
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(0, result.getAffectedObject().size());
    }
}
