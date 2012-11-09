/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import at.heli.scada.bl.exception.BLException;
import at.heli.scada.dal.InstallationRepository;
import at.heli.scada.dal.MeasurementRepository;
import at.heli.scada.dal.exception.DalException;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Installation;
import at.heli.scada.entities.Measurement;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class StatisticService {
    
    private static final Logger log = Logger.getLogger(StatisticService.class.getName());
    
    private MeasurementRepository mrepo;
    private InstallationRepository irepo;
    
    public StatisticService(MeasurementRepository mrepo, InstallationRepository irepo)
    {
        this.mrepo = mrepo;
        this.irepo = irepo;
    }
    
    public void getInstallationState(Installation i) throws BLException
    {
        
    }
    
    public void getStatisticPerDay(Customer c) throws BLException
    {
        List<Installation> insts;
        try
        {
            insts = irepo.getByCustomerId(c);
            List<Measurement> measures;
            for(Installation i : insts)
            {
                
                
            }
        }
        catch(DalException err)
        {
            throw new BLException();
        }
    }
    
    public void getStatisticPerMonth(Customer c)
    {
        
    }
    
    public void getStatisticPerYear(Customer c)
    {
        
    }
    
}
