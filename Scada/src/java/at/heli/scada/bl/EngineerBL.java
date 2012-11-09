/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.bl.exception.BLException;
import at.heli.scada.dal.exception.DalException;
import at.heli.scada.dal.*;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Engineer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class EngineerBL {
    
    private static final Logger log = Logger.getLogger(EngineerBL.class.getName());
    
    private Repository<Engineer> repo;
    
    public EngineerBL(Repository<Engineer> repo)
    {
        this.repo = repo;
    }
    
    public List<Customer> getCustomers(Engineer e)
    {
        return (List<Customer>)e.getCustomerCollection();
    }
    
    public Engineer getEngineerById(int id) throws BLException
    {
        Engineer e = new Engineer();
        try
        {
            e = repo.getById(id);
            log.log(Level.INFO, "engineer fetched with id " + id);
        }
        catch(DalException err)
        {
            log.log(Level.SEVERE, "cannot fetch engineer with id " + id, err);
            throw new BLException("cannot fetch engineer with id " + id, err);
        }
        return e;
    }
    
}
