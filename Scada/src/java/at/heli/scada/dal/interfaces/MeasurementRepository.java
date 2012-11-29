/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal.interfaces;

import at.heli.scada.entities.InstallationState;
import at.heli.scada.entities.Statistic;
import at.heli.scada.entities.Installation;
import at.heli.scada.entities.Measurement;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface MeasurementRepository {
    
    public void save(Measurement entity) throws DalException;
    public void delete(Measurement entity) throws DalException;
    public Measurement getById(int id) throws DalException;
    public List<Measurement> getAll() throws DalException;
    public List<Statistic> getPerDay(Installation i, Date date) throws DalException;
    public List<Statistic> getPerMonth(Installation i, Date date) throws DalException;
    public List<Statistic> getPerYear(Installation i, Date date) throws DalException;
    public List<Measurement> getCurrentValues(Installation i) throws DalException;
    
}
