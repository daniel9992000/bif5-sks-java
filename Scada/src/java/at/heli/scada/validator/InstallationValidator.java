/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.validator;

import at.heli.scada.entities.Installation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniel
 */
public class InstallationValidator implements Validator<Installation> {
    
    boolean valid;
    List<ValidationError> errors;

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }

    @Override
    public void validate(Installation obj) {
        errors = new ArrayList<ValidationError>();
        valid = true;
    }
    
}
