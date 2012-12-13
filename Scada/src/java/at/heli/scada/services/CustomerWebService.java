/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.services;

import at.heli.scada.bl.CustomerService;
import at.heli.scada.bl.InstallationService;
import at.heli.scada.bl.interfaces.BLException;
import at.heli.scada.bl.interfaces.ExecutionResult;
import at.heli.scada.bl.interfaces.ICustomerService;
import at.heli.scada.bl.interfaces.IInstallationService;
import at.heli.scada.dal.interfaces.CustomerRepository;
import at.heli.scada.dal.interfaces.EngineerRepository;
import at.heli.scada.dal.interfaces.InstallationRepository;
import at.heli.scada.dal.qualifier.DbCustomerQualifier;
import at.heli.scada.dal.qualifier.DbEngineerQualifier;
import at.heli.scada.dal.qualifier.DbInstallationQualifier;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Installation;
import at.heli.scada.validator.ValidationError;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author daniel
 */
@WebService(serviceName = "CustomerWebService")
public class CustomerWebService {

    @Inject @DbCustomerQualifier
    private CustomerRepository crepo;
    
    @Inject @DbEngineerQualifier
    private EngineerRepository erepo;
    
    @Inject @DbInstallationQualifier
    private InstallationRepository irepo;
    
    private IInstallationService ibl;
    private ICustomerService cbl;
    
    @WebMethod(operationName = "createCustomer")
    public String createCustomer(Customer c)
    {
        cbl = new CustomerService(irepo, crepo);
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
        ibl = new InstallationService(null, irepo, null);
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
