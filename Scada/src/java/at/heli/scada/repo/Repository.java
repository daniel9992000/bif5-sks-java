/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.repo;

import java.util.List;

/**
 *
 * @author daniel
 */
public interface Repository<T> {
    
    public void save(T entity);
    public void delete(T entity);
    public T getById(int id);
    public List<T> getAll();
    
}
