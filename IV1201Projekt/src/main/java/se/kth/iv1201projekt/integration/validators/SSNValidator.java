package se.kth.iv1201projekt.integration.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Checks if name contains number or is bigger than the database limit
 *
 * @author Samy
 */
@FacesValidator("ssnValidator")
public class SSNValidator implements Validator {

    private final String numberRegex = "/^\\d+$/";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String param = value.toString();

        if (param.matches(numberRegex) || param.length() != 10) {
            FacesMessage msg = new FacesMessage("Invalid Social Security Number");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
