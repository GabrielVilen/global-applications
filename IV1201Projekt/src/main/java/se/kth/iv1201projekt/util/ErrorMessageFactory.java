/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.util;

import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/**
 * En klass som läser in error messages ifrån resourcebundle och returnerar en sträng
 * @author Samy
 */
public class ErrorMessageFactory {
    
    /**
     * Returns error message to the corresponding errorKey
     * 
     * @param errorKey the key name in the properties file
     * @return error message in correct language
     */
    public static String getErrorMessage(String errorKey){
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "error");
        return bundle.getString(errorKey);
    }
}
