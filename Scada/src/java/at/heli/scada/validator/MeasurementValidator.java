/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.validator;

import at.heli.scada.entities.Measurement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniel
 */
public class MeasurementValidator implements Validator<Measurement> {

    private boolean valid;
    private List<ValidationError> errors;
    
    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }

    @Override
    public void validate(Measurement obj) {
        errors = new ArrayList<ValidationError>();
        valid = true;
    }
    
}
