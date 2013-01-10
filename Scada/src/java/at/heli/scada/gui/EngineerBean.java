/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.gui;

import at.heli.scada.bl.interfaces.BLException;
import at.heli.scada.bl.interfaces.ExecutionResult;
import at.heli.scada.bl.interfaces.ICustomerService;
import at.heli.scada.bl.interfaces.IEngineerService;
import at.heli.scada.bl.interfaces.IStatisticService;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Engineer;
import at.heli.scada.entities.Installation;
import at.heli.scada.entities.Person;
import at.heli.scada.entities.Statistic;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

/**
 *
 * @author daniel
 */
@Named(value = "engineerBean")
@SessionScoped
public class EngineerBean implements Serializable {
    
    @Inject @LoggedIn 
    private Person current;
    
    @Inject
    IEngineerService ebl;
    
    @Inject 
    ICustomerService cbl;
    
    @Inject
    IStatisticService sbl;
   
    private Customer currentCustomer;
    private Installation currentInstallation;
    private Date date;
    private DataModel<Installation> installations;
    private DataModel<Customer> customers;
    
    private DataModel<Statistic> dayStat;
    private DataModel<Statistic> monthStat;
    private DataModel<Statistic> yearStat;
    
    
    @PostConstruct
    public void init()
    {
        date = new Date();
        try
        {
            ExecutionResult<List<Customer>> result = ebl.getCustomers(current.getPersonid());
            customers = new ListDataModel<Customer>(result.getAffectedObject());
        }
        catch(BLException e)
        {
           
        }
    }
    
    public String getInstallation()
    {
        setCurrentCustomer(customers.getRowData());
        try
        {
            ExecutionResult<List<Installation>> result = cbl.getInstallations(currentCustomer);
            installations = new ListDataModel<Installation>(result.getAffectedObject());
        }
        catch(BLException e)
        {
           
        }
        
        return "installations";
    }
    
    public String getStatistics()
    {
        setCurrentInstallation(installations.getRowData());
        try
        {
            ExecutionResult<List<Statistic>> result = sbl.getStatisticPerDay(getCurrentInstallation().getInstallationid(), date);
            dayStat = new ListDataModel<Statistic>(result.getAffectedObject());
            result = sbl.getStatisticPerMonth(getCurrentInstallation().getInstallationid(), date);
            monthStat = new ListDataModel<Statistic>(result.getAffectedObject());
            result = sbl.getStatisticPerYear(getCurrentInstallation().getInstallationid(), date);
            yearStat = new ListDataModel<Statistic>(result.getAffectedObject());
            
        }
        catch(BLException e)
        {
            
        }
        return "statistics";
    }

    /**
     * @return the current
     */
    public Person getCurrent() {
        return current;
    }

    /**
     * @return the customers
     */
    public DataModel<Customer> getCustomers() {
        return customers;
    }

    /**
     * @return the installations
     */
    public DataModel<Installation> getInstallations() {
        return installations;
    }

    /**
     * @return the currentCustomer
     */
    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    /**
     * @param currentCustomer the currentCustomer to set
     */
    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    /**
     * @return the dayStat
     */
    public DataModel<Statistic> getDayStat() {
        return dayStat;
    }

    /**
     * @return the currentInstallation
     */
    public Installation getCurrentInstallation() {
        return currentInstallation;
    }

    /**
     * @param currentInstallation the currentInstallation to set
     */
    public void setCurrentInstallation(Installation currentInstallation) {
        this.currentInstallation = currentInstallation;
    }

    /**
     * @return the monthStat
     */
    public DataModel<Statistic> getMonthStat() {
        return monthStat;
    }

    /**
     * @return the yearStat
     */
    public DataModel<Statistic> getYearStat() {
        return yearStat;
    }
}
