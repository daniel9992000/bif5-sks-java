/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl.interfaces;

import at.heli.scada.entities.Installation;
import at.heli.scada.entities.Measurement;
import at.heli.scada.entities.Statistic;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author daniel
 */
public interface IStatisticService {
    
    public List<Measurement> getInstallationState(Installation i) throws BLException;
    public ExecutionResult<Map<Installation, List<Statistic>>> getStatisticPerDay(int customerid, Date date) throws BLException;
    public ExecutionResult<Map<Installation, List<Statistic>>> getStatisticPerMonth(int customerid, Date date) throws BLException;
    public ExecutionResult<Map<Installation, List<Statistic>>> getStatisticPerYear(int customerid, Date date) throws BLException;
    
}
