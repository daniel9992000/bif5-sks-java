/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.services;

import at.heli.scada.bl.interfaces.BLException;
import at.heli.scada.bl.interfaces.ExecutionResult;
import at.heli.scada.bl.interfaces.ICustomerService;
import at.heli.scada.bl.interfaces.IInstallationService;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Installation;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.inject.Inject;

/**
 *
 * @author daniel
 */
@WebService(serviceName = "CustomerWebService")
public class CustomerWebService {
    
    @Inject
    private IInstallationService ibl;
    
    @Inject
    private ICustomerService cbl;
    
    @WebMethod(operationName = "createCustomer")
    public String createCustomer(Customer c)
    {
        try
        {
            ExecutionResult<Customer> result = cbl.createCustomer(c);
            if(result.isSuccess())
            {
                return "Customer created!";
            }
            else
            {  
                return "Customer not created!";
            }
        }
        catch(BLException err)
        {
            return "Error while creating customer! " + err.toString();
        }
    }
    
    @WebMethod(operationName = "createInstallation")
    public String createInstallation(Installation i)
    {
        try
        {
            ExecutionResult<Installation> result = ibl.createInstallation(i);
            if(result.isSuccess())
            {
                return "Installation created!";
            }
            else
            {
                return "Installation not created!";
            }
        }
        catch(BLException err)
        {
            return "Error while creating installation! " + err.toString();
        }
    }
}
