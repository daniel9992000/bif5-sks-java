/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.dal.exception.DalException;
import at.heli.scada.entities.Customer;
import at.heli.scada.entities.Installation;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface InstallationRepository extends Repository<Installation> {
    
    public List<Installation> getByCustomerId(Customer c) throws DalException;
    
}