/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.bl.Statistic;
import at.heli.scada.dal.exception.DalException;
import at.heli.scada.dal.interfaces.MeasurementRepository;
import at.heli.scada.entities.Installation;
import at.heli.scada.entities.Measurement;
import at.heli.scada.entities.MeasurementType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daniel
 */
public class MockMeasurementRepository implements MeasurementRepository {
    
    List<Measurement> measures;
    List<MeasurementType> type;
    
    public MockMeasurementRepository()
    {
        measures = new ArrayList<Measurement>();
        type = new ArrayList<MeasurementType>();
    }

    @Override
    public void save(Measurement entity) throws DalException {
        measures.add(entity);
    }

    @Override
    public void delete(Measurement entity) throws DalException {
        measures.remove(entity);
    }

    @Override
    public Measurement getById(int id) throws DalException {
        for(Measurement m : measures)
        {
            if(m.getMeasid() == id)
            {
                return m;
            }
        }
        return null;
    }

    @Override
    public List<Measurement> getAll() throws DalException {
        return measures;
    }

    @Override
    public List<Statistic> getPerDay(Installation i, Date date) throws DalException {
        
        
        
        return null;
    }

    @Override
    public List<Statistic> getPerMonth(Installation i, Date date) throws DalException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Statistic> getPerYear(Installation i, Date date) throws DalException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Measurement> getCurrentValues(Installation i) throws DalException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
