/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl.interfaces;

import at.heli.scada.entities.*;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ICustomerService {
    
    public ExecutionResult<Customer> createCustomer(Customer c) throws BLException;
    public ExecutionResult<Customer> getCustomer(int id) throws BLException;
    public ExecutionResult<List<Installation>> getInstallations(Customer c) throws BLException;
    
}
