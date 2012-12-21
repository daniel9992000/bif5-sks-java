/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.services;

import at.heli.scada.bl.interfaces.BLException;
import at.heli.scada.bl.interfaces.IPersonService;
import com.sun.xml.wss.impl.callback.PasswordValidationCallback;
import com.sun.xml.wss.impl.callback.PasswordValidationCallback.PasswordValidationException;
import com.sun.xml.wss.impl.callback.PasswordValidationCallback.Request;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author daniel
 */
public class Authenticator implements PasswordValidationCallback.PasswordValidator {
    
    private static final Logger log = Logger.getLogger(Authenticator.class.getName());
    
    @Inject 
    private IPersonService pbl;

    @Override
    public boolean validate(Request rqst) throws PasswordValidationException {
        
        PasswordValidationCallback.PlainTextPasswordRequest plainTextRequest 
            = (PasswordValidationCallback.PlainTextPasswordRequest) rqst;
        
        try 
        {
            
            if(plainTextRequest.getUsername().equals("daniel") && 
                plainTextRequest.getPassword().equals("1234567"))
            {
                return true;
            }
            
            //return pbl.authenticatePerson(plainTextRequest.getUsername(), plainTextRequest.getPassword());
        } 
        catch (Exception ex) 
        {
            log.log(Level.SEVERE, "Error while authenticating user!", ex);
            throw new PasswordValidationException("Error while authenticating user", ex);
        }
        
        return false;
    }
    
}
