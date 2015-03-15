package se.kth.iv1201projekt.integration.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import se.kth.iv1201projekt.util.ErrorMessageFactory;

/**
 * Validator that checks if username is more than 3 character or does not
 * contain a blank space
 *
 * @author Gabriel
 */
@FacesValidator("usernameValidator")
public class UsernameValidator implements Validator {

    private final String numberRegex = "^\\s*$/";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String param = value.toString();

        if (param.matches(numberRegex) || param.length() < 3) {
            FacesMessage msg = new FacesMessage(ErrorMessageFactory.getErrorMessage("invalidusername"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
