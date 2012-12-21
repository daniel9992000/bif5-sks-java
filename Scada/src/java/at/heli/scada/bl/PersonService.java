/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.bl.interfaces.BLException;
import at.heli.scada.bl.interfaces.ExecutionResult;
import at.heli.scada.bl.interfaces.IPersonService;
import at.heli.scada.dal.interfaces.DalException;
import at.heli.scada.dal.interfaces.PersonRepository;
import at.heli.scada.entities.Person;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author daniel
 */
@Named
@Alternative
public class PersonService implements IPersonService {
    
    private static final Logger log = Logger.getLogger(PersonService.class.getName());
    
    private PersonRepository prepo;
    
    @Inject
    public PersonService(PersonRepository prepo)
    {
        this.prepo = prepo;
    }

    @Override
    public boolean authenticatePerson(String username, String password) throws BLException {
        Person tmp = null;
        
        try 
        {
            tmp = prepo.getByUsername(username);
            
            if(tmp == null)
            {
                return false;
            }
            
            if(password.equals(tmp.getPassword()))
            {
                return true;
            }
            else
            {
                return false;
            }
        } 
        catch (DalException ex) 
        {
            log.log(Level.SEVERE, "Error while authenticating user!", ex);
            throw new BLException("Error while authenticating user!", ex);
        }
    }
}
