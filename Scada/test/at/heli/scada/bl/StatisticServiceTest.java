/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.entities.Installation;
import java.util.Date;
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstallationState method, of class StatisticService.
     */
    @Test
    public void testGetInstallationState() throws Exception {
        System.out.println("getInstallationState");
        Installation i = null;
        StatisticService instance = null;
        List expResult = null;
        List result = instance.getInstallationState(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatisticPerDay method, of class StatisticService.
     */
    @Test
    public void testGetStatisticPerDay() throws Exception {
        System.out.println("getStatisticPerDay");
        int customerid = 0;
        Date date = null;
        StatisticService instance = null;
        ExecutionResult expResult = null;
        ExecutionResult result = instance.getStatisticPerDay(customerid, date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatisticPerMonth method, of class StatisticService.
     */
    @Test
    public void testGetStatisticPerMonth() throws Exception {
        System.out.println("getStatisticPerMonth");
        int customerid = 0;
        Date date = null;
        StatisticService instance = null;
        ExecutionResult expResult = null;
        ExecutionResult result = instance.getStatisticPerMonth(customerid, date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatisticPerYear method, of class StatisticService.
     */
    @Test
    public void testGetStatisticPerYear() throws Exception {
        System.out.println("getStatisticPerYear");
        int customerid = 0;
        Date date = null;
        StatisticService instance = null;
        ExecutionResult expResult = null;
        ExecutionResult result = instance.getStatisticPerYear(customerid, date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
