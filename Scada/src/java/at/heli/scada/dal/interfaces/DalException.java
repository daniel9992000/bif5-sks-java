/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal.interfaces;

/**
 *
 * @author daniel
 */
public class DalException extends Exception {
    
    public DalException()
    {
        super();
    }
    
    public DalException(String message)
    {
        super(message);
    }
    
    public DalException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public DalException(Throwable cause)
    {
        super(cause);
    }
    
}
