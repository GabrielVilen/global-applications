/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.businesslogic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 * En generell bean som hanterar saker som kan ske på alla sidor. T.ex. byte av språk
 * @author Samy
 */
@Named(value = "languageBean")
@SessionScoped
public class LanguageBean implements Serializable{
    
    private String localeTag;
    private static Map<String, Object> languages;
    
    /**
     * Initialiserar denna en gång. Hämtar alla supportade Locales i Facesconfig 
     * för att alla tillgängliga språk ska kunna visas.
     */
    static{
        languages = new HashMap<>();
        Application facesApp = FacesContext.getCurrentInstance().getApplication();
        Locale defLoc = facesApp.getDefaultLocale();
        languages.put(defLoc.getDisplayLanguage(), defLoc);
        
        Iterator<Locale> locales = facesApp.getSupportedLocales();
        while(locales.hasNext()){
            Locale loc=locales.next();
            languages.put(loc.getDisplayLanguage(), loc);
        }
    }

    public String getLocale() {
        return localeTag;
    }

    public void setLocale(String locale) {
        this.localeTag = locale;
    }

    public Map<String, Object> getLanguages() {
        return languages;
    }
    
    public void localeChanged(ValueChangeEvent ev){
        String newLocaleValue = ev.getNewValue().toString();
        for(Map.Entry<String,Object> entry : languages.entrySet()){
            if(entry.getValue().toString().equals(newLocaleValue)){
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)entry.getValue());
            }
        }
    }
    
}
