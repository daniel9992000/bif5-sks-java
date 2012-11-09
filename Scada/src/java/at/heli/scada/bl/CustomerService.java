/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.bl.exception.BLException;
import at.heli.scada.dal.exception.DalException;
import at.heli.scada.dal.*;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Installation;
import at.heli.scada.validator.CustomerValidator;
import at.heli.scada.validator.Validator;
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
    
    public void createCustomer(Customer c) throws BLException
    {
        try
        {
            Validator<Customer> validator = new CustomerValidator();
            validator.validate(c);
            
            if(validator.isValid())
            {
                crepo.save(c);
                log.log(Level.INFO, "Customer saved!");
            }
            else
            {
                log.log(Level.SEVERE, "Validation failed");
                for(String error : validator.getErrors())
                {
                    
                }
            }
        }
        catch(DalException err)
        {
            log.log(Level.SEVERE, "cannot save customer!", err);
            throw new BLException("cannot save customer!", err);
        }
    }
    
    public Customer getCustomer(int id) throws BLException
    {
        Customer tmp;
        try
        {
            tmp = crepo.getById(id);
        }
        catch(DalException err)
        {
            throw new BLException();
        }
        
        return tmp;
    }
    
    public List<Installation> getInstallations(Customer c) throws BLException
    {
        List<Installation> tmp;
        try
        {
            tmp = irepo.getByCustomerId(c);
        }
        catch(DalException err)
        {
            throw new BLException();
        }
        
        return tmp;
    }
}
