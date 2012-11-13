/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.validator;

import at.heli.scada.entities.Customer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author daniel
 */
public class CustomerValidator implements Validator<Customer> {

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
    public void validate(Customer obj) {
       errors = new ArrayList();            
       valid = true;
       
       if(obj.getFirstname().isEmpty())
       {
           valid=false;
           errors.add(new ValidationError("Firstname is required!", "firstname"));
       }
       if(obj.getLastname().isEmpty())
       {
           valid=false;
           errors.add(new ValidationError("Lastname is required!", "lastname"));
       }
       if(obj.getEmail().isEmpty())
       {
           valid=false;
           errors.add(new ValidationError("E-Mail is required!", "email"));
       }
       if(obj.getUsername().isEmpty())
       {
           valid=false;
           errors.add(new ValidationError("Username is required!", "username"));
       }
       
       if(obj.getPassword().isEmpty())
       {
           valid=false;
           errors.add(new ValidationError("Password is required!", "password"));
       }
       else if(obj.getPassword().length() <= 6 )
       {
           valid=false;
           errors.add(new ValidationError("Password must be longer than 6 signs", "password"));
       }
    }
}
