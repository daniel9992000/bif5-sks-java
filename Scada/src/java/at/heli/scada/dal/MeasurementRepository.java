/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.dal.exception.DalException;
import at.heli.scada.entities.Installation;
import at.heli.scada.entities.Measurement;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface MeasurementRepository extends Repository<Measurement> {
    
    public List<Measurement> getPerDay(Installation i, Calendar time) throws DalException;
    public List<Measurement> getPerMonth(Installation i, Calendar time) throws DalException;
    public List<Measurement> getPerYear(Installation i, Calendar time) throws DalException;
    
}
