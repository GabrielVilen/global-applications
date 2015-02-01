package se.kth.iv1201projekt.integration.validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;
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
@FacesValidator("dateValidator")
public class DateValidator implements Validator{
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try {
            String format=null;
            String param = value.toString().replace('T', ' ');
            
            if(param.contains("T"))
                format="YYYY-MM-dd HH:mm";
            else
                format="YYYY-MM-dd";
            
            Logger logger = Logger.getLogger(getClass().getName());
            logger.info(param);
            System.out.println(param);
            DateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);
            Date date = df.parse(param);
        } catch (ParseException ex) {
            FacesMessage msg = new FacesMessage("Invalid Date");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
       
    }

}
