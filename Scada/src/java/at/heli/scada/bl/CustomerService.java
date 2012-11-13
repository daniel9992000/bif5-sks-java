/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.dal.interfaces.InstallationRepository;
import at.heli.scada.dal.interfaces.Repository;
import at.heli.scada.bl.exception.BLException;
import at.heli.scada.dal.*;
import at.heli.scada.dal.exception.*;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Installation;
import at.heli.scada.validator.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class CustomerService {
    
    private static final Logger log = Logger.getLogger(CustomerService.class.getName());
    
    private InstallationRepository irepo;
    private Repository<Customer> crepo;
    
    public CustomerService(InstallationRepository irepo, Repository<Customer> crepo)
    {
        this.irepo = irepo;
        this.crepo = crepo;
    }
    
    public ExecutionResult<Customer> createCustomer(Customer c) throws BLException
    {
        try
        {
            Validator<Customer> validator = new CustomerValidator();
            validator.validate(c);
            
            if(validator.isValid())
            {
                crepo.save(c);
                log.log(Level.INFO, "Customer saved!");
                return new ExecutionResult<Customer>(c);
            }
            else
            {
                log.log(Level.INFO, "{0} Validation errors", validator.getErrors().size());
                for(ValidationError ve : validator.getErrors())
                {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Error on property ");
                    sb.append(ve.getField());
                    sb.append(": ");
                    sb.append(ve.getMessage());
                    log.log(Level.INFO, sb.toString());
                }
                return new ExecutionResult<Customer>(c, validator.getErrors());
            }
        }
        catch(DalException err)
        {
            log.log(Level.SEVERE, "cannot save customer!", err);
            throw new BLException("cannot save customer!", err);
        }
    }
    
    public ExecutionResult<Customer> getCustomer(int id) throws BLException
    {
        Customer tmp;
        try
        {
            log.log(Level.INFO, "Fetching customer id {0}", id);
            tmp = crepo.getById(id);
            return new ExecutionResult<Customer>(tmp);
        }
        catch(DalException err)
        {
            log.log(Level.SEVERE, "Cannot fetch customer id " + id, err);
            throw new BLException("Cannot fetch customer", err);
        }
    }
    
    public ExecutionResult<List<Installation>> getInstallations(Customer c) throws BLException
    {
        List<Installation> tmp;
        try
        {
            tmp = irepo.getByCustomerId(c);
            
            return new ExecutionResult<List<Installation>>(tmp);
        }
        catch(DalException err)
        {
            throw new BLException();
        }
        
    }
}
