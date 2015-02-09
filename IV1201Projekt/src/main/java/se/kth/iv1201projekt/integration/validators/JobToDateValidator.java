package se.kth.iv1201projekt.integration.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Gabriel
 */
@FacesValidator("jobToDateValidator")
public class JobToDateValidator implements Validator {
    
    //private final String numberRegex="^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String param = value.toString();
            
        if (param.length() != 8) {
            FacesMessage msg = new FacesMessage("Invalid job start-date: must be 8 characters");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
