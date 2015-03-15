package se.kth.iv1201projekt.util;

import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/**
 * Class reading error messages from resource bundle and returning a string
 *
 * @author Samy
 */
public class ErrorMessageFactory {

    /**
     * Returns error message to the corresponding errorKey
     *
     * @param errorKey the key name in the properties file
     * @return error message in correct language
     */
    public static String getErrorMessage(String errorKey) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "error");
        return bundle.getString(errorKey);
    }
}
