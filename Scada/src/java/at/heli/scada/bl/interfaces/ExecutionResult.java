/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl.interfaces;

import at.heli.scada.validator.ValidationError;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ExecutionResult<T> {
    
    private List<ValidationError> errors; 
    private T affectedObject;
    private boolean success;
    
    public ExecutionResult()
    {
        
    }
    
    public ExecutionResult(T affectedObject)
    {
        this.affectedObject = affectedObject;
        errors = null;
        this.success = true;
    }
    
    public ExecutionResult(T affectedObject, List<ValidationError> errors)
    {
        this.affectedObject = affectedObject;
        this.errors = errors;
        this.success = false;
    }

    /**
     * @return the errors
     */
    public List<ValidationError> getErrors() {
        return errors;
    }

    /**
     * @return the affectedObject
     */
    public T getAffectedObject() {
        return affectedObject;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }
    
}
