package se.kth.iv1201projekt.integration.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Checks if the length of the job info is more than 10 characters
 *
 * @author Gabriel
 */
@FacesValidator("jobInfoValidator")
public class JobInfoValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String param = value.toString();

        if (param.length() < 10) {
            FacesMessage msg = new FacesMessage("Invalid job info: must be more than 10 characters");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
