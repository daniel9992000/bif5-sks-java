/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.bl.interfaces.BLException;
import at.heli.scada.bl.interfaces.ExecutionResult;
import at.heli.scada.bl.interfaces.IStatisticService;
import at.heli.scada.dal.interfaces.CustomerRepository;
import at.heli.scada.dal.interfaces.DalException;
import at.heli.scada.dal.interfaces.InstallationRepository;
import at.heli.scada.dal.interfaces.MeasurementRepository;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Installation;
import at.heli.scada.entities.InstallationState;
import at.heli.scada.entities.Measurement;
import at.heli.scada.entities.Statistic;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author daniel
 */
@Named
@Alternative
@Stateless
public class StatisticService implements IStatisticService {
    
    private static final Logger log = Logger.getLogger(StatisticService.class.getName());
    
    private MeasurementRepository mrepo;
    private InstallationRepository irepo;
    private CustomerRepository crepo;
    
    public StatisticService()
    {
        
    }
    
    @Inject
    public StatisticService(MeasurementRepository mrepo, InstallationRepository irepo, CustomerRepository crepo)
    {
        this.mrepo = mrepo;
        this.irepo = irepo;
        this.crepo = crepo;
    }
    
    @Override
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
    
    @Override
    public ExecutionResult<List<Statistic>> getStatisticPerDay(int installationid, Date date) throws BLException
    { 
        List<Statistic> tmp;
        try
        {
            log.log(Level.INFO, "Fetching installation id {0}", installationid);
            Installation i = irepo.getById(installationid);
            if(i == null)
            {
                throw new BLException("Installation does not exist with ID " + installationid);
            }
            
            log.log(Level.INFO, "Fetching statistics for installation id {0}", installationid);
                
            tmp = mrepo.getPerDay(i, date);
            log.log(Level.INFO, "Statistic per day for installation id {0} created", installationid);
             
        }
        catch(DalException err)
        {
            log.log(Level.SEVERE, "Error while creating statistics per day for installation id {0}", installationid);
            throw new BLException("Error while creating statistics per day!", err);
        }
        
        return new ExecutionResult<List<Statistic>>(tmp);
    }
    
    @Override
    public ExecutionResult<List<Statistic>> getStatisticPerMonth(int installationid, Date date) throws BLException
    {
        List<Statistic> tmp;
        try
        {
            log.log(Level.INFO, "Fetching installation id {0}", installationid);
            Installation i = irepo.getById(installationid);
            if(i == null)
            {
                throw new BLException("Installation does not exist with ID " + installationid);
            }
            
            log.log(Level.INFO, "Fetching statistics for installation id {0}", installationid);
                
            tmp = mrepo.getPerMonth(i, date);
            log.log(Level.INFO, "Statistic per day for installation id {0} created", installationid);
             
        }
        catch(DalException err)
        {
            log.log(Level.SEVERE, "Error while creating statistics per month for installation id {0}", installationid);
            throw new BLException("Error while creating statistics per month!", err);
        }
        
        return new ExecutionResult<List<Statistic>>(tmp);
    }
    
    @Override
    public ExecutionResult<List<Statistic>> getStatisticPerYear(int installationid, Date date) throws BLException
    {
        List<Statistic> tmp;
        try
        {
            log.log(Level.INFO, "Fetching installation id {0}", installationid);
            Installation i = irepo.getById(installationid);
            if(i == null)
            {
                throw new BLException("Installation does not exist with ID " + installationid);
            }
            
            log.log(Level.INFO, "Fetching statistics for installation id {0}", installationid);
                
            tmp = mrepo.getPerYear(i, date);
            log.log(Level.INFO, "Statistic per day for installation id {0} created", installationid);
             
        }
        catch(DalException err)
        {
            log.log(Level.SEVERE, "Error while creating statistics per year for installation id {0}", installationid);
            throw new BLException("Error while creating statistics per year!", err);
        }
        
        return new ExecutionResult<List<Statistic>>(tmp);
    }
    
}
