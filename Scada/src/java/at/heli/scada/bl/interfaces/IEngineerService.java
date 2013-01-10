/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl.interfaces;

import at.heli.scada.entities.Customer;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface IEngineerService {
    public ExecutionResult<List<Customer>> getCustomers(int engineerid) throws BLException ;
    
}
