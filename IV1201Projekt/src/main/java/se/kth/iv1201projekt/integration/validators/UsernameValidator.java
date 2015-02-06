
package se.kth.iv1201projekt.integration.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator that checks if username is more than 4 character or does not contain a blank space
 * 
 * @author Gabriel
 */

@FacesValidator("usernameValidator")
public class UsernameValidator implements Validator {

    private final String numberRegex="^\\s*$/";
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
       String param = value.toString();
       
       if(param.matches(numberRegex)||param.length()<4){
           FacesMessage msg = new FacesMessage("Invalid username: cannot be less than 4 char or contain a blank space");
           msg.setSeverity(FacesMessage.SEVERITY_ERROR);
           throw new ValidatorException(msg);
       }
    }
    
    
}
