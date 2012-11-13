/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.validator;

/**
 *
 * @author daniel
 */
public class ValidationError {
    private String message;
    private String field;
    
    public ValidationError(String message, String field)
    {
        this.message = message;
        this.field = field;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the field
     */
    public String getField() {
        return field;
    }
}
