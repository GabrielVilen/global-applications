/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.businesslogic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 * En generell bean som hanterar saker som kan ske på alla sidor. T.ex. byte av språk
 * @author Samy
 */
@Named(value = "generalBean")
@SessionScoped
public class GeneralBean implements Serializable{
    
    private String localeTag;
    private static Map<String, Object> languages;
    
    static{
        languages = new HashMap<>();
        languages.put("English", Locale.ENGLISH);
        languages.put("Svenska", new Locale("sv", ""));
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
