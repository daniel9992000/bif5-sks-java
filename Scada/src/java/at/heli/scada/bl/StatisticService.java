/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.bl.exception.BLException;
import at.heli.scada.dal.interfaces.InstallationRepository;
import at.heli.scada.dal.interfaces.MeasurementRepository;
import at.heli.scada.dal.interfaces.Repository;
import at.heli.scada.dal.exception.DalException;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Installation;
import at.heli.scada.entities.Measurement;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author daniel
 */
public class StatisticService {
    
    private static final Logger log = Logger.getLogger(StatisticService.class.getName());
    
    private MeasurementRepository mrepo;
    private InstallationRepository irepo;
    private Repository<Customer> crepo;
    
    public StatisticService(MeasurementRepository mrepo, InstallationRepository irepo, Repository<Customer> crepo)
    {
        this.mrepo = mrepo;
        this.irepo = irepo;
        this.crepo = crepo;
    }
    
    public List<Measurement> getInstallationState(Installation i) throws BLException
    {
        Map<Installation, List<InstallationState>> stats = new HashMap<Installation, List<InstallationState>>();
        List<Measurement> mes;
        try
        {
            List<InstallationState> tmp;
            
            mes = mrepo.getCurrentValues(i);
            //stats.put(i, tmp);
              
        }
        catch(DalException err)
        {
            throw new BLException();
        }
        
        return mes;
    }
    
    public ExecutionResult<Map<Installation, List<Statistic>>> getStatisticPerDay(int customerid, Date date) throws BLException
    {
        //For each installation, a whole statistic
        Map<Installation, List<Statistic>> stats = new HashMap<Installation, List<Statistic>>();
        
        try
        {
            log.log(Level.INFO, "Fetching customer id {0}", customerid);
            Customer c = crepo.getById(customerid);
            if(c == null)
            {
                throw new BLException("Customer does not exist with ID " + customerid);
            }
            
            List<Statistic> tmp;
            
            log.log(Level.INFO, "Fetching all installations for customer id {0}", customerid);
            for(Installation i : irepo.getByCustomerId(c))
            {
                
                tmp = mrepo.getPerDay(i, date);
                log.log(Level.INFO, "Statistic per day for installation id {0} created", i.getInstallationid());
                stats.put(i, tmp);
            }  
        }
        catch(DalException err)
        {
            log.log(Level.SEVERE, "Error while creating statistics per day for customer id {0}", customerid);
            throw new BLException("Error while creating statistics per day!", err);
        }
        
        return new ExecutionResult<Map<Installation, List<Statistic>>>(stats);
    }
    
    public ExecutionResult<Map<Installation, List<Statistic>>> getStatisticPerMonth(int customerid, Date date) throws BLException
    {
        //For each installation, a whole statistic
        Map<Installation, List<Statistic>> stats = new HashMap<Installation, List<Statistic>>();
        
        try
        {
            log.log(Level.INFO, "Fetching customer id {0}", customerid);
            Customer c = crepo.getById(customerid);
            if(c == null)
            {
                throw new BLException("Customer does not exist with ID " + customerid);
            }
            
            List<Statistic> tmp;
            
            log.log(Level.INFO, "Fetching all installations for customer id {0}", customerid);
            for(Installation i : irepo.getByCustomerId(c))
            {
                tmp = mrepo.getPerMonth(i, date);
                log.log(Level.INFO, "Statistic per month for installation id {0} created", i.getInstallationid());
                stats.put(i, tmp);
            }  
        }
        catch(DalException err)
        {
            log.log(Level.SEVERE, "Error while creating statistics per month for customer id {0}", customerid);
            throw new BLException("Error while creating statistics per month!", err);
        }
        
        return new ExecutionResult<Map<Installation, List<Statistic>>>(stats);
    }
    
    public ExecutionResult<Map<Installation, List<Statistic>>> getStatisticPerYear(int customerid, Date date) throws BLException
    {
        //For each installation, a whole statistic
        Map<Installation, List<Statistic>> stats = new HashMap<Installation, List<Statistic>>();
        
        try
        {
            log.log(Level.INFO, "Fetching customer id {0}", customerid);
            Customer c = crepo.getById(customerid);
            if(c == null)
            {
                throw new BLException("Customer does not exist with ID " + customerid);
            }
            
            List<Statistic> tmp;
            
            log.log(Level.INFO, "Fetching all installations for customer id {0}", customerid);
            for(Installation i : irepo.getByCustomerId(c))
            {
                tmp = mrepo.getPerYear(i, date);
                log.log(Level.INFO, "Statistic per month for installation id {0} created", i.getInstallationid());
                stats.put(i, tmp);
            }  
        }
        catch(DalException err)
        {
            log.log(Level.SEVERE, "Error while creating statistics per year for customer id {0}", customerid);
            throw new BLException("Error while creating statistics per year!", err);
        }
        
        return new ExecutionResult<Map<Installation, List<Statistic>>>(stats);
    }
    
}
