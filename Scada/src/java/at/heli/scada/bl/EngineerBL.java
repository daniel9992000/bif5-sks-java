/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.dal.DalException;
import at.heli.scada.dal.Repository;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Engineer;
import at.heli.scada.entities.Installation;
import at.heli.scada.entities.Person;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author daniel
 */
@Named(value = "engineerBL")
@SessionScoped
public class EngineerBL implements Serializable {
    private static final Logger log = Logger.getLogger(EngineerBL.class.getName());
    
    @Inject @DbCustomer
    private Repository<Customer> crepo;
    
    //@Inject 
    //private Repository<Installation> irepo;
    
    private Engineer e;
    private DataModel<Customer> customers;
    
    public DataModel getCustomers()
    {
        return customers;
    }
    
    @PostConstruct
    public void init() {
        log.entering("EngineerBL", "init");
        try {
            List<Customer> cusList = crepo.getAll();
            customers = new ListDataModel<Customer>(cusList);
        } catch(DalException err) {
            log.log(Level.SEVERE, err.getMessage(), err);
        }
    }
    
    public String customerList() {
        init();
        return "customerList";
    }
    
    public void createCustomer()
    {
        
    }
    
    public void createInstallation()
    {
        
    }
    
}
