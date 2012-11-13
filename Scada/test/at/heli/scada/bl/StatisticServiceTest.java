/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.dal.MockCustomerRepository;
import at.heli.scada.dal.MockInstallationRepository;
import at.heli.scada.dal.interfaces.CustomerRepository;
import at.heli.scada.dal.interfaces.InstallationRepository;
import at.heli.scada.dal.interfaces.MeasurementRepository;
import at.heli.scada.entities.Installation;
import java.sql.Date;
import java.util.List;
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
public class StatisticServiceTest {
    
    static MeasurementRepository mrepo;
    static InstallationRepository irepo;
    static CustomerRepository crepo;
    
    public StatisticServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mrepo = null;
        irepo = new MockInstallationRepository();
        crepo = new MockCustomerRepository();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstallationState method, of class StatisticService.
     */
    @Test
    public void getStatisticsPerDay() throws Exception {
        //Arrange
        StatisticService sbl = new StatisticService(mrepo, irepo, crepo);
        int customerid = 0;
        Date date = Date.valueOf("2012-11-11");
        
        
        //Act
        sbl.getStatisticPerDay(customerid, date);
        
        //Assert
    }
}
