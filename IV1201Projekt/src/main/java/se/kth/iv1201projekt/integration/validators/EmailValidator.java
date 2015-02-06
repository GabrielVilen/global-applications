package se.kth.iv1201projekt.integration.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Checks if name contains number or is bigger than the database limit
 * @author Samy
 */
@FacesValidator("emailValidator")
public class EmailValidator implements Validator{
        
    private final String emailRegex="[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,4}";
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
       String param = value.toString();
       
       if(!param.matches(emailRegex)){
           FacesMessage msg = new FacesMessage("Invalid Email");
           msg.setSeverity(FacesMessage.SEVERITY_ERROR);
           throw new ValidatorException(msg);
       }
    }

}
