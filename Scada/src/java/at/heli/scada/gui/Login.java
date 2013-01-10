/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.gui;

import at.heli.scada.bl.interfaces.BLException;
import at.heli.scada.bl.interfaces.IPersonService;
import at.heli.scada.entities.Person;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author daniel
 */

@SessionScoped
@Named
public class Login implements Serializable {

    @Inject
    private IPersonService pbl;
    
    private String username;
    private String password;
    
    private Person person;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String login() throws BLException
    {
        person = pbl.login(username, password);
        
        if(person == null)
        {
            return "login";
        }
        else if(person.getDtype().equals("Customer"))
        {
            return "customer";
        }
        else
        {
            return "engineer";
        }
    }
    
    public void logout()
    {
        person = null;
    }
    
    public boolean isLoggedIn()
    {
        return person != null;
    }
    
    @Produces @LoggedIn 
    Person getCurrentPerson()
    {
        return person;
    }

}
