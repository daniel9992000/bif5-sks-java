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
    private List<String> errors; 
    
    @Override
    public boolean isValid() {
        return valid;
    }
    
    @Override
    public List<String> getErrors() {
        return errors;
    }

    @Override
    public void validate(Customer obj) {
       errors = new ArrayList();            
       valid = true;
       
       if(obj.getFirstname().isEmpty())
       {
           valid=false;
           errors.add("Firstname is required!");
       }
       if(obj.getLastname().isEmpty())
       {
           valid=false;
           errors.add("Lastname is required!");
       }
       if(obj.getEmail().isEmpty())
       {
           valid=false;
           errors.add("E-Mail is required!");
       }
       if(obj.getUsername().isEmpty())
       {
           valid=false;
           errors.add("Username is required!");
       }
       if(obj.getPassword().isEmpty())
       {
           valid=false;
           errors.add("Password is required!");
       }
       if(obj.getPassword().length() <= 6 )
       {
           valid=false;
           errors.add("Password must be longer than 6 signs");
       }
    }
}
