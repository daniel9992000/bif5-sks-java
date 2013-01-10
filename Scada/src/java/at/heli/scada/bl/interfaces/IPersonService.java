/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl.interfaces;

import at.heli.scada.entities.Person;

/**
 *
 * @author daniel
 */
public interface IPersonService {
    
    public boolean authenticatePerson(String username, String password) throws BLException;
    public Person login(String username, String password) throws BLException;
    
}
