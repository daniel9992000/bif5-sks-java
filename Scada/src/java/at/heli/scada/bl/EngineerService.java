/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.bl.interfaces.ExecutionResult;
import at.heli.scada.bl.interfaces.BLException;
import at.heli.scada.bl.interfaces.IEngineerService;
import at.heli.scada.dal.interfaces.DalException;
import at.heli.scada.dal.interfaces.CustomerRepository;
import at.heli.scada.dal.interfaces.EngineerRepository;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Engineer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author daniel
 */
@Named
@Alternative
@Stateless
public class EngineerService implements IEngineerService {
    
    private static final Logger log = Logger.getLogger(EngineerService.class.getName());
    
    private EngineerRepository erepo;
    private CustomerRepository crepo;

    public EngineerService() {
    }
    
    @Inject
    public EngineerService(EngineerRepository erepo, CustomerRepository crepo)
    {
        this.erepo = erepo;
        this.crepo = crepo;
    }
    
    @Override
    public ExecutionResult<List<Customer>> getCustomers(int engineerid) throws BLException
    {
        List<Customer> tmp = null;
        try
        {
            log.log(Level.INFO, "Fetching engineer id {0}", engineerid);
            Engineer e = erepo.getById(engineerid);
            if(e == null)
            {
                throw new BLException("Engineer does not exist with ID " + engineerid);
            }
            
            log.log(Level.INFO, "Fetching all customers for engineer id {0}", engineerid);
            tmp = crepo.getByEngineerId(e);
            
            return new ExecutionResult<List<Customer>>(tmp);
        }
        catch(DalException err)
        {
            log.log(Level.SEVERE, "Error while fetching all customers for engineer id " + engineerid, err);
            throw new BLException("Error while fetching all customers", err);
        }
    }
    
}
