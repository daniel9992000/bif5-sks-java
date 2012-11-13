/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal.interfaces;

import at.heli.scada.dal.exception.DalException;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface Repository<T> {
    
    public void save(T entity) throws DalException;
    public void delete(T entity) throws DalException;
    public T getById(int id) throws DalException;
    public List<T> getAll() throws DalException;
    
}
