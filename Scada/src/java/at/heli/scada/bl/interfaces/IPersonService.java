/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl.interfaces;

/**
 *
 * @author daniel
 */
public interface IPersonService {
    
    public boolean authenticatePerson(String username, String password) throws BLException;
    
}
