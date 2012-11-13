/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.validator;

import java.util.List;

/**
 *
 * @author daniel
 */
public interface Validator<T> {
    
    public boolean isValid();
    public List<ValidationError> getErrors();
    public void validate(T obj);
}
