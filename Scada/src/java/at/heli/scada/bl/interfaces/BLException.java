/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl.interfaces;

/**
 *
 * @author daniel
 */
public class BLException extends Exception {

    /**
     * Creates a new instance of
     * <code>BLException</code> without detail message.
     */
    public BLException() {
    }

    /**
     * Constructs an instance of
     * <code>BLException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public BLException(String msg) {
        super(msg);
    }
    
    public BLException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public BLException(Throwable cause)
    {
        super(cause);
    }
}
