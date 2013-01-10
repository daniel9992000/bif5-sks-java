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
import at.heli.scada.entities.Installation;
import at.heli.scada.entities.Person;
import at.heli.scada.entities.Statistic;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

/**
 *
 * @author daniel
 */
@Named(value = "customerBean")
@SessionScoped
public class CustomerBean implements Serializable {
    @Inject @LoggedIn 
    private Person current;
    
    @Inject 
    ICustomerService cbl;
    
    @Inject
    IStatisticService sbl;
    
    private Installation currentInstallation;
    private Date date;
    private DataModel<Installation> installations;
    
    private DataModel<Statistic> dayStat;
    private DataModel<Statistic> monthStat;
    private DataModel<Statistic> yearStat;
    
    
    @PostConstruct
    public void init()
    {
        date = new Date();
        try
        {
            ExecutionResult<Customer> c = cbl.getCustomer(current.getPersonid());
            ExecutionResult<List<Installation>> result = cbl.getInstallations(c.getAffectedObject());
            installations = new ListDataModel<Installation>(result.getAffectedObject());
        }
        catch(BLException e)
        {
           
        }
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
        return "statcustomer";
    }

    /**
     * @return the current
     */
    public Person getCurrent() {
        return current;
    }

    /**
     * @return the installations
     */
    public DataModel<Installation> getInstallations() {
        return installations;
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
