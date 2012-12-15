/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal.interfaces;

import at.heli.scada.entities.Person;

/**
 *
 * @author daniel
 */
public interface PersonRepository {
    public Person getByUsername(String username) throws DalException;
}
