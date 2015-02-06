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
@FacesValidator("nameValidator")
public class NameValidator implements Validator{
        
    private final String numberRegex="/^\\d+$/";
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
       String param = value.toString();
       
       if(param.matches(numberRegex)||param.length()<2 || param.length()>255){
           FacesMessage msg = new FacesMessage("A name can't contain a number and must be between 3 and 255");
           msg.setSeverity(FacesMessage.SEVERITY_ERROR);
           throw new ValidatorException(msg);
       }
    }

}
