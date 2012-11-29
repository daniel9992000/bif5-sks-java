/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal.interfaces;

import at.heli.scada.entities.MeasurementType;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface TypeRepository {
    public void save(MeasurementType entity) throws DalException;
    public void delete(MeasurementType entity) throws DalException;
    public MeasurementType getById(int id) throws DalException;
    public List<MeasurementType> getAll() throws DalException;
}
